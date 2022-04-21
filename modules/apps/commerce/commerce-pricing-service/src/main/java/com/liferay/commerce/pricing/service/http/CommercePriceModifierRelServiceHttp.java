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

package com.liferay.commerce.pricing.service.http;

import com.liferay.commerce.pricing.service.CommercePriceModifierRelServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>CommercePriceModifierRelServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Riccardo Alberti
 * @generated
 */
public class CommercePriceModifierRelServiceHttp {

	public static com.liferay.commerce.pricing.model.CommercePriceModifierRel
			addCommercePriceModifierRel(
				HttpPrincipal httpPrincipal, long commercePriceModifierId,
				String className, long classPK,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceModifierRelServiceUtil.class,
				"addCommercePriceModifierRel",
				_addCommercePriceModifierRelParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commercePriceModifierId, className, classPK,
				serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.commerce.pricing.model.CommercePriceModifierRel)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void deleteCommercePriceModifierRel(
			HttpPrincipal httpPrincipal, long commercePriceModifierRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceModifierRelServiceUtil.class,
				"deleteCommercePriceModifierRel",
				_deleteCommercePriceModifierRelParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commercePriceModifierRelId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.commerce.pricing.model.CommercePriceModifierRel
			fetchCommercePriceModifierRel(
				HttpPrincipal httpPrincipal, long commercePriceModifierId,
				String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceModifierRelServiceUtil.class,
				"fetchCommercePriceModifierRel",
				_fetchCommercePriceModifierRelParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commercePriceModifierId, className, classPK);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.commerce.pricing.model.CommercePriceModifierRel)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.commerce.pricing.model.CommercePriceModifierRel>
			getCategoriesCommercePriceModifierRels(
				HttpPrincipal httpPrincipal, long commercePriceModifierId,
				String name, int start, int end) {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceModifierRelServiceUtil.class,
				"getCategoriesCommercePriceModifierRels",
				_getCategoriesCommercePriceModifierRelsParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commercePriceModifierId, name, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.commerce.pricing.model.CommercePriceModifierRel>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getCategoriesCommercePriceModifierRelsCount(
		HttpPrincipal httpPrincipal, long commercePriceModifierId,
		String name) {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceModifierRelServiceUtil.class,
				"getCategoriesCommercePriceModifierRelsCount",
				_getCategoriesCommercePriceModifierRelsCountParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commercePriceModifierId, name);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static long[] getClassPKs(
			HttpPrincipal httpPrincipal, long commercePriceModifierRelId,
			String className)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceModifierRelServiceUtil.class, "getClassPKs",
				_getClassPKsParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commercePriceModifierRelId, className);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (long[])returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.commerce.pricing.model.CommercePriceModifierRel
			getCommercePriceModifierRel(
				HttpPrincipal httpPrincipal, long commercePriceModifierRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceModifierRelServiceUtil.class,
				"getCommercePriceModifierRel",
				_getCommercePriceModifierRelParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commercePriceModifierRelId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.commerce.pricing.model.CommercePriceModifierRel)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.commerce.pricing.model.CommercePriceModifierRel>
				getCommercePriceModifierRels(
					HttpPrincipal httpPrincipal,
					long commercePriceModifierRelId, String className)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceModifierRelServiceUtil.class,
				"getCommercePriceModifierRels",
				_getCommercePriceModifierRelsParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commercePriceModifierRelId, className);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.commerce.pricing.model.CommercePriceModifierRel>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.commerce.pricing.model.CommercePriceModifierRel>
				getCommercePriceModifierRels(
					HttpPrincipal httpPrincipal,
					long commercePriceModifierRelId, String className,
					int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<com.liferay.commerce.pricing.model.
							CommercePriceModifierRel> orderByComparator)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceModifierRelServiceUtil.class,
				"getCommercePriceModifierRels",
				_getCommercePriceModifierRelsParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commercePriceModifierRelId, className, start, end,
				orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.commerce.pricing.model.CommercePriceModifierRel>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getCommercePriceModifierRelsCount(
			HttpPrincipal httpPrincipal, long commercePriceModifierRelId,
			String className)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceModifierRelServiceUtil.class,
				"getCommercePriceModifierRelsCount",
				_getCommercePriceModifierRelsCountParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commercePriceModifierRelId, className);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.commerce.pricing.model.CommercePriceModifierRel>
			getCommercePriceModifiersRels(
				HttpPrincipal httpPrincipal, String className, long classPK) {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceModifierRelServiceUtil.class,
				"getCommercePriceModifiersRels",
				_getCommercePriceModifiersRelsParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, className, classPK);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.commerce.pricing.model.CommercePriceModifierRel>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.commerce.pricing.model.CommercePriceModifierRel>
			getCommercePricingClassesCommercePriceModifierRels(
				HttpPrincipal httpPrincipal, long commercePriceModifierId,
				String title, int start, int end) {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceModifierRelServiceUtil.class,
				"getCommercePricingClassesCommercePriceModifierRels",
				_getCommercePricingClassesCommercePriceModifierRelsParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commercePriceModifierId, title, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.commerce.pricing.model.CommercePriceModifierRel>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getCommercePricingClassesCommercePriceModifierRelsCount(
		HttpPrincipal httpPrincipal, long commercePriceModifierId,
		String title) {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceModifierRelServiceUtil.class,
				"getCommercePricingClassesCommercePriceModifierRelsCount",
				_getCommercePricingClassesCommercePriceModifierRelsCountParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commercePriceModifierId, title);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.commerce.pricing.model.CommercePriceModifierRel>
			getCPDefinitionsCommercePriceModifierRels(
				HttpPrincipal httpPrincipal, long commercePriceModifierId,
				String name, String languageId, int start, int end) {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceModifierRelServiceUtil.class,
				"getCPDefinitionsCommercePriceModifierRels",
				_getCPDefinitionsCommercePriceModifierRelsParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commercePriceModifierId, name, languageId, start,
				end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (java.util.List
				<com.liferay.commerce.pricing.model.CommercePriceModifierRel>)
					returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getCPDefinitionsCommercePriceModifierRelsCount(
		HttpPrincipal httpPrincipal, long commercePriceModifierId, String name,
		String languageId) {

		try {
			MethodKey methodKey = new MethodKey(
				CommercePriceModifierRelServiceUtil.class,
				"getCPDefinitionsCommercePriceModifierRelsCount",
				_getCPDefinitionsCommercePriceModifierRelsCountParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commercePriceModifierId, name, languageId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		CommercePriceModifierRelServiceHttp.class);

	private static final Class<?>[]
		_addCommercePriceModifierRelParameterTypes0 = new Class[] {
			long.class, String.class, long.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[]
		_deleteCommercePriceModifierRelParameterTypes1 = new Class[] {
			long.class
		};
	private static final Class<?>[]
		_fetchCommercePriceModifierRelParameterTypes2 = new Class[] {
			long.class, String.class, long.class
		};
	private static final Class<?>[]
		_getCategoriesCommercePriceModifierRelsParameterTypes3 = new Class[] {
			long.class, String.class, int.class, int.class
		};
	private static final Class<?>[]
		_getCategoriesCommercePriceModifierRelsCountParameterTypes4 =
			new Class[] {long.class, String.class};
	private static final Class<?>[] _getClassPKsParameterTypes5 = new Class[] {
		long.class, String.class
	};
	private static final Class<?>[]
		_getCommercePriceModifierRelParameterTypes6 = new Class[] {long.class};
	private static final Class<?>[]
		_getCommercePriceModifierRelsParameterTypes7 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[]
		_getCommercePriceModifierRelsParameterTypes8 = new Class[] {
			long.class, String.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[]
		_getCommercePriceModifierRelsCountParameterTypes9 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[]
		_getCommercePriceModifiersRelsParameterTypes10 = new Class[] {
			String.class, long.class
		};
	private static final Class<?>[]
		_getCommercePricingClassesCommercePriceModifierRelsParameterTypes11 =
			new Class[] {long.class, String.class, int.class, int.class};
	private static final Class<?>[]
		_getCommercePricingClassesCommercePriceModifierRelsCountParameterTypes12 =
			new Class[] {long.class, String.class};
	private static final Class<?>[]
		_getCPDefinitionsCommercePriceModifierRelsParameterTypes13 =
			new Class[] {
				long.class, String.class, String.class, int.class, int.class
			};
	private static final Class<?>[]
		_getCPDefinitionsCommercePriceModifierRelsCountParameterTypes14 =
			new Class[] {long.class, String.class, String.class};

}