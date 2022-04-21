/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.bean.portlet.cdi.extension.internal.mvc;

import com.liferay.bean.portlet.cdi.extension.internal.scope.RedirectBeanContext;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.lang.annotation.Annotation;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.AnnotatedField;
import javax.enterprise.inject.spi.AnnotatedMethod;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.ProcessAnnotatedType;

import javax.inject.Inject;

import javax.mvc.Controller;
import javax.mvc.RedirectScoped;

import javax.portlet.annotations.ActionMethod;
import javax.portlet.annotations.RenderMethod;
import javax.portlet.annotations.ServeResourceMethod;

import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;

import javax.ws.rs.BeanParam;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/**
 * @author Neil Griffin
 */
public class MVCExtension {

	public static void step1BeforeBeanDiscovery(
		BeanManager beanManager, BeforeBeanDiscovery beforeBeanDiscovery) {

		beforeBeanDiscovery.addInterceptorBinding(
			BeanValidationInterceptorBinding.class);
		beforeBeanDiscovery.addInterceptorBinding(
			ControllerInterceptorBinding.class);
		beforeBeanDiscovery.addInterceptorBinding(
			CsrfValidationInterceptorBinding.class);
		beforeBeanDiscovery.addQualifier(
			BeanValidationMessageInterpolator.class);
		beforeBeanDiscovery.addQualifier(
			BeanValidationMessageInterpolator.class);
		beforeBeanDiscovery.addQualifier(ParamConverterProviders.class);
		beforeBeanDiscovery.addQualifier(PortletParam.class);
		beforeBeanDiscovery.addQualifier(ViewEngines.class);
		beforeBeanDiscovery.addScope(RedirectScoped.class, true, false);

		Class<?>[] beanClasses = {
			BeanValidationInterceptor.class, BeanValidationProducer.class,
			BindingResultProducer.class, ConfigurationProducer.class,
			ControllerInterceptor.class, CsrfValidationInterceptor.class,
			EncodersProducer.class, ModelsProducer.class,
			MVCContextProducer.class, ParamConverterProvidersProducer.class,
			PortletParamProducer.class, ViewEngineContextProducer.class,
			ViewEnginesProducer.class
		};

		for (Class<?> beanClass : beanClasses) {
			beforeBeanDiscovery.addAnnotatedType(
				beanManager.createAnnotatedType(beanClass), null);
		}
	}

	public static <T> void step2ProcessAnnotatedType(
		ProcessAnnotatedType<T> processAnnotatedType) {

		AnnotatedType<T> annotatedType =
			processAnnotatedType.getAnnotatedType();

		boolean modifiedMethods = false;

		Set<AnnotatedMethod<? super T>> annotatedMethods =
			new LinkedHashSet<>();

		boolean typeLevelController = annotatedType.isAnnotationPresent(
			Controller.class);

		for (AnnotatedMethod<? super T> annotatedMethod :
				annotatedType.getMethods()) {

			ActionMethod actionMethod = annotatedMethod.getAnnotation(
				ActionMethod.class);

			RenderMethod renderMethod = annotatedMethod.getAnnotation(
				RenderMethod.class);

			ServeResourceMethod serveResourceMethod =
				annotatedMethod.getAnnotation(ServeResourceMethod.class);

			if (((actionMethod != null) || (renderMethod != null) ||
				 (serveResourceMethod != null)) &&
				(typeLevelController ||
				 annotatedMethod.isAnnotationPresent(Controller.class))) {

				Set<Annotation> methodAnnotations = new LinkedHashSet<>(
					annotatedMethod.getAnnotations());

				methodAnnotations.add(_controllerInterceptorBinding);

				modifiedMethods = true;

				if ((actionMethod != null) || (serveResourceMethod != null)) {
					methodAnnotations.add(_csrfValidationInterceptorBinding);
				}

				ValidateOnExecution validateOnExecution =
					annotatedMethod.getAnnotation(ValidateOnExecution.class);

				boolean addBeanValidationInterceptor = true;

				if (validateOnExecution != null) {
					ExecutableType[] executableTypes =
						validateOnExecution.type();

					for (ExecutableType executableType : executableTypes) {
						if (executableType == ExecutableType.NONE) {
							addBeanValidationInterceptor = false;

							break;
						}
					}
				}

				if (addBeanValidationInterceptor) {
					methodAnnotations.add(_beanValidationInterceptorBinding);
				}

				annotatedMethod = new ModifiedMethod<>(
					annotatedMethod, methodAnnotations);
			}

			annotatedMethods.add(annotatedMethod);
		}

		boolean modifiedFieldAnnotations = false;

		Set<AnnotatedField<? super T>> annotatedFields = new LinkedHashSet<>();

		for (AnnotatedField<? super T> annotatedField :
				annotatedType.getFields()) {

			Set<Annotation> fieldAnnotations = new LinkedHashSet<>(
				annotatedField.getAnnotations());

			for (Class<?> clazz : _UNSUPPORTED_ANNOTATION_CLASSES) {
				@SuppressWarnings("unchecked")
				Class<? extends Annotation> unsupportedAnnotationClass =
					(Class<? extends Annotation>)clazz;

				Annotation unsupportedAnnotation = annotatedField.getAnnotation(
					unsupportedAnnotationClass);

				if (unsupportedAnnotation != null) {
					fieldAnnotations.remove(unsupportedAnnotation);

					modifiedFieldAnnotations = true;

					if (_log.isWarnEnabled()) {
						_log.warn(
							StringBundler.concat(
								"Removed the @",
								unsupportedAnnotationClass.getSimpleName(),
								" annotation from ", annotatedField,
								" because it is unsupported"));
					}
				}
			}

			BeanParam beanParam = annotatedField.getAnnotation(BeanParam.class);
			CookieParam cookieParam = annotatedField.getAnnotation(
				CookieParam.class);
			FormParam formParam = annotatedField.getAnnotation(FormParam.class);
			HeaderParam headerParam = annotatedField.getAnnotation(
				HeaderParam.class);
			QueryParam queryParam = annotatedField.getAnnotation(
				QueryParam.class);

			if ((beanParam != null) || (cookieParam != null) ||
				(formParam != null) || (headerParam != null) ||
				(queryParam != null)) {

				if (!annotatedField.isAnnotationPresent(Inject.class)) {
					fieldAnnotations.add(_inject);
					modifiedFieldAnnotations = true;
				}

				if (beanParam == null) {
					fieldAnnotations.add(_portletParam);
					modifiedFieldAnnotations = true;
				}
			}

			if (modifiedFieldAnnotations) {
				annotatedField = new ModifiedField<>(
					annotatedField, fieldAnnotations);
			}

			annotatedFields.add(annotatedField);
		}

		if (modifiedMethods || modifiedFieldAnnotations) {
			processAnnotatedType.setAnnotatedType(
				new ModifiedAnnotatedType<>(
					annotatedType, annotatedFields, annotatedMethods));
		}
	}

	public static void step3AfterBeanDiscovery(
		AfterBeanDiscovery afterBeanDiscovery) {

		afterBeanDiscovery.addContext(new RedirectBeanContext());
	}

	private static final Class<?>[] _UNSUPPORTED_ANNOTATION_CLASSES =
		new Class<?>[] {MatrixParam.class, PathParam.class};

	private static final Log _log = LogFactoryUtil.getLog(MVCExtension.class);

	private static final Annotation _beanValidationInterceptorBinding =
		new BeanValidationInterceptorBinding() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return BeanValidationInterceptorBinding.class;
			}

		};

	private static final Annotation _controllerInterceptorBinding =
		new ControllerInterceptorBinding() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return ControllerInterceptorBinding.class;
			}

		};

	private static final Annotation _csrfValidationInterceptorBinding =
		new CsrfValidationInterceptorBinding() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return CsrfValidationInterceptorBinding.class;
			}

		};

	private static final Annotation _inject = new Inject() {

		@Override
		public Class<? extends Annotation> annotationType() {
			return Inject.class;
		}

	};

	private static final Annotation _portletParam = new PortletParam() {

		@Override
		public Class<? extends Annotation> annotationType() {
			return PortletParam.class;
		}

	};

}