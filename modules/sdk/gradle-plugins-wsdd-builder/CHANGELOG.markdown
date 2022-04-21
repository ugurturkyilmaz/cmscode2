# Liferay Gradle Plugins WSDD Builder Change Log

## 1.0.17 - 2021-05-18

### Commits
- [COMMERCE-4584] Fix buildWSDD (9928360b66)
- [LPS-105380] Partial revert (aafd3818ae)
- [LPS-105380] Auto SF, XMLDTDVersionCheck (a7cb83d66b)

## 1.0.16 - 2020-11-02

### Commits
- [LPS-111291] Update readme (a87d2593e9)
- [LPS-111291] Gradle 5.6.4 tests (40f4f9e2f3)
- [LPS-111291] Update README.markdown (eea12b7f8f)
- [LPS-111291] Update plugins Gradle version (003c3832b0)
- [LPS-105380] Rename vars, follow case of name of type (46c8ad4dd5)
- [LPS-105380] Rename variable: cause -> throwable (d8ff33c6c2)
- [LPS-108328] Remove version number check (69092ebf90)

### Dependencies
- [LPS-111291] Update the com.liferay.gradle.util dependency to version 1.0.44.
- [LPS-115020] Update the com.liferay.gradle.util dependency to version 1.0.43.
- [LPS-88645] Update the com.liferay.gradle.util dependency to version 1.0.42.
- [LPS-88645] Update the com.liferay.gradle.util dependency to version 1.0.41.
- [LPS-113624] Update the com.liferay.gradle.util dependency to version 1.0.40.
- [LPS-110422] Update the com.liferay.gradle.util dependency to version 1.0.39.
- [LPS-111896] Update the com.liferay.gradle.util dependency to version 1.0.38.
- [LPS-88645] Update the com.liferay.gradle.util dependency to version 1.0.37.
- [LPS-110283] Update the com.liferay.gradle.util dependency to version 1.0.36.

## 1.0.15 - 2020-03-04

### Commits
- [LPS-106149] Baseline (becb322fa3)
- [LPS-106149] Cacheable tasks (5f1911b5ba)
- [LPS-106167] Partial revert (06136ec8) (c7fc18dd4a)
- [LPS-108328] Revert "LPS-108328 Allow hotfix patterns" (e75ccbcf8d)
- [LPS-108328] Allow hotfix patterns (9c332ad565)
- [LPS-107008] Regen (899a13784d)
- [LPS-106167] Update build.gradle (06136ec832)
- [LPS-106167] Use com.liferay.petra.string.StringPool instead (9aa4d72e67)

### Dependencies
- [LPS-106149] Update the com.liferay.gradle.util dependency to version 1.0.35.

## 1.0.14 - 2019-12-13

### Commits
- [LPS-100515] Update README.markdown (694b3791de)
- [LPS-100515] Update plugins Gradle version (448efac158)
- [LPS-102700] Fix bnd error (include literal dot) (d65985bae3)
- [LPS-101089] Remove usages on private package classes. (b598761012)
- [LPS-84119] Use 'osgi.core' instead of 'org.osgi.core' (01606b6fb1)
- [LPS-85609] Update readme (c182ff396d)
- [LPS-85609] Simplify gradleTest (a8b0feff31)
- [LPS-86406] Manual cleanup (1e03c415e5)
- [LPS-85609] Use Gradle 4.10.2 (9aa90f8961)

### Dependencies
- [LPS-105247] Update the com.liferay.portal.tools.wsdd.builder dependency to
version 1.0.11.
- [LPS-96247] Update the com.liferay.gradle.util dependency to version 1.0.34.

## 1.0.13 - 2018-11-19

### Dependencies
- [LPS-87466] Update the com.liferay.gradle.util dependency to version 1.0.33.

## 1.0.12 - 2018-11-16

### Commits
- [LPS-87192] Set the Eclipse task property gradleVersion (040b2abdee)
- [LPS-87192] Add variable gradleVersion (no logic changes) (2f7c0b2fe4)
- [LPS-85609] Fix for CI (test only 4.10.2) (4eed005731)
- [LPS-85609] Test plugins up to Gradle 4.10.2 (60905bc960)
- [LPS-85609] Update supported Gradle versions (d79b89682b)
- [LPS-86589] Update readme (4280a3d596)
- [LPS-86589] Test Gradle plugins from Gradle 2.14.1 to 3.5.1 (6df521a506)
- [LPS-71117] Test plugins with Gradle up to 3.5.1 (c3e12d1cf3)
- [LPS-71117] Update supported Gradle versions in READMEs (fdcc16c0d4)
- [LPS-82128] Remove unneeded references (c7f81cbc23)
- [LPS-79799] Revert "LPS-79799 Build Services." (0a3609613f)
- [LPS-79799] Build Services. (ab6ba23411)
- [LPS-78772] Auto SF (remove whitespace) (004b577478)
- [LPS-77425] Partial revert of d25f48516a9ad080bcbd50e228979853d3f2dda5
(60d3a950d6)
- [LPS-77425] Increment all major versions (d25f48516a)
- [LRDOCS-4319] Update Gradle plugin README intro descriptions for consistency
(72104bde58)
- [LRDOCS-4319] Update Gradle plugin BND descriptions for consistency
(e1495e8e8d)
- [LPS-76644] Enable Gradle plugins publishing (8bfdfd53d7)
- [LPS-76644] Add description to Gradle plugins (5cb7b30e6f)

### Dependencies
- [LPS-87466] Update the com.liferay.gradle.util dependency to version 1.0.32.
- [LPS-84094] Update the com.liferay.gradle.util dependency to version 1.0.31.
- [LPS-84094] Update the com.liferay.gradle.util dependency to version 1.0.30.
- [LPS-77425] Update the com.liferay.gradle.util dependency to version 1.0.29.

## 1.0.10 - 2017-12-12

### Commits
- [LPS-73967] Remove 'build.auto.upgrade' properties. (eac2021954)
- [LPS-73855] Generated (450f3672d3)
- [LPS-66709] Update supported Gradle versions in READMEs (06e315582b)
- [LPS-70677] No need to look into the local Maven repository during testing
(452be84220)
- [LPS-70060] Test plugins with Gradle 3.3 (09bed59a42)
- [LPS-66709] README typo (283446e516)
- [LPS-66709] Add supported Gradle versions in READMEs (e0d9458520)
- [LPS-69271] ran formatJavadoc (left out changes to Journal module)
(ab05227c41)
- [LPS-69259] Test plugins with Gradle 3.2.1 (72873ed836)
- [LPS-69259] Test plugins with Gradle 3.2 (dec6105d3d)
- [LPS-66709] Add command-line arguments in the READMEs (4c6dc97741)

### Dependencies
- [LPS-73584] Update the com.liferay.gradle.util dependency to version 1.0.29.
- [LPS-73584] Update the com.liferay.gradle.util dependency to version 1.0.28.
- [LPS-72914] Update the com.liferay.gradle.util dependency to version 1.0.27.

## 1.0.9 - 2016-10-10

### Commits
- [LPS-66709] Edit READMEs (2072601ff5)
- [LPS-66709] Add links to portal tools (8baf0882de)
- [LPS-68231] Test plugins with Gradle 3.1 (49ec4cdbd8)
- [LPS-67658] gradlew buildService in the gradle-plugins-wsdd-builder test
(44ab962c4e)
- [LPS-67658] Convert gradle-plugins-wsdd-builder sample into a smoke test
(d275dcceb2)
- [LPS-67658] Configure GradleTest in gradle-plugins-wsdd-builder (99d908cb3d)
- [LPS-67658] Need "compileOnly" to keep dependencies out of "compile"
(4a3cd0bc9d)
- [LPS-67658] These plugins must work with Gradle 2.5+ (5b963e363d)
- [LPS-66709] Fix plugin IDs in README (54b8d38db6)
- [LPS-66709] Edit Gradle plugin README files for Service Builder, TLD
Formatter, WSDD Builder, WSDL Builder, XML Formatter, and XSD Builder.
(c2e7e02b4a)
- [LPS-66709] README for gradle-plugins-wsdd-builder (7df7aafb18)
- [LPS-66064] Better glob to distinguish asm-*.jar from asm-commons-*.jar
(d50ced317d)
- [LPS-66064] Remove use of "-liferay-includeresource" (bbd6b63415)
- [LPS-64816] Update Gradle plugin samples (3331002e5d)
- [LPS-61099] Delete build.xml in modules (c9a7e1d370)
- [LPS-64021] Prefix directive (0eb9b8b7d8)
- [LPS-64021] Apply for "sdk" (50aea4bb04)
- [LPS-64021] Use directive instead (9c31b9fc18)
- [LPS-63943] This is done automatically now (f1e42382d9)
- [LPS-61420] Source formatting (0df5dd83d6)
- [LPS-62883] Update gradle-plugins/build.gradle (20fc2457e6)
- [LPS-61420] Fit on single line (9cc5731c19)
- [LPS-61088] Remove classes and resources dir from Include-Resource
(1b0e1275bc)

### Dependencies
- [LPS-65749] Update the com.liferay.gradle.util dependency to version 1.0.26.
- [LPS-65086] Update the com.liferay.gradle.util dependency to version 1.0.25.

## 1.0.7 - 2015-11-11

### Commits
- [LPS-60310] Fix sample (598adced16)
- [LPS-60310] Set task default when "war" is applied (c268f93969)
- [LPS-60310] Set task defaults (441b9d1d32)
- [LPS-60310] Set arg default value with a callable instead (14c469efea)
- [LPS-60310] We use sourceSets, so we apply JavaPlugin for consistency
(10af19d4ef)
- [LPS-60310] Add Gradle input annotations (4e65a1a3d0)
- [LPS-60310] Allow closures and callables as values for the task args
(73ac480314)
- [LPS-60310] Allow custom classpath (47993d520b)
- [LPS-60310] Allow custom args (7ee91f06d5)
- [LPS-60310] projectDir is already the default value of workingDir (830703f866)
- [LPS-60310] Allow custom main class (2d43a3ef3e)
- [LPS-59564] Update directory layout for "sdk" modules (ea19635556)
- [LPS-58330] The portal tool dependencies are only used to embed single *Args
classes, so they should be considered "provided" (da7c77ffbc)
- [LPS-51081] Remove modules' Eclipse project files (b3f19f9012)
- [LPS-51081] Replace modules' Ant files with Gradle alternatives (9e60160a85)
- [LPS-51081] Remove modules' Ivy files (076b384eef)

### Dependencies
- [LPS-60310] Update the com.liferay.gradle.util dependency to version 1.0.23.
- [LPS-58467] Update the com.liferay.gradle.util dependency to version 1.0.19.
- [LPS-51081] Update the com.liferay.gradle.util dependency to version 1.0.18.
- [LPS-51081] Update the com.liferay.gradle.util dependency to version 1.0.17.
- [LPS-51081] Update the com.liferay.gradle.util dependency to version 1.0.16.
- [LPS-51081] Update the com.liferay.gradle.util dependency to version 1.0.15.

## 1.0.6 - 2015-07-27

### Commits
- [LPS-51081] Update to Gradle 2.5 (3aa4c1f053)
- [LPS-51801] Use Gradle API to grab dependencies from the local Gradle
installation (48f775db44)
- [LPS-51081] Ran "ant reset-gradle init-gradle" (9ab363b842)
- [LPS-51081] Consistency with Gradle 2.4 dependencies (6d4008a98c)

### Dependencies
- [LPS-51081] Update the com.liferay.gradle.util dependency to version 1.0.14.
- [LPS-51081] Update the com.liferay.gradle.util dependency to version 1.0.13.
- [LPS-51081] Update the com.liferay.gradle.util dependency to version 1.0.12.
- [LPS-51081] Update the com.liferay.gradle.util dependency to version 1.0.11.
- [LPS-51081] Update the org.gradle.gradle-base-services dependency to version
2.4.
- [LPS-51081] Update the org.gradle.gradle-base-services-groovy dependency to
version 2.4.
- [LPS-51081] Update the org.gradle.gradle-core dependency to version 2.4.
- [LPS-51081] Update the org.gradle.gradle-plugins dependency to version 2.4.
- [LPS-51081] Update the groovy-all dependency to version 2.3.10.

## 1.0.5 - 2015-06-17

### Commits
- [LPS-56469] Find dependencies directly from the sourceSet classpath and not
from the configuration (213c724b20)
- [LPS-55187] Use only 1.0.6 (f63748d15a)
- [LPS-51081] use only 1.0.5 (4d9c09dfce)
- [LPS-51081] Update to Gradle 2.4 (9966e0be8d)

## 1.0.4 - 2015-05-29

### Commits
- [LPS-55608] Fix Gradle 2.4 deprecation warning by deferring configuration
resolution (5343a0699e)

## 1.0.3 - 2015-05-09

### Commits
- [LPS-51081] released 1.0.3 and prep for next version (7781a3085a)
- [LPS-51081] Typo (d2c9c2d0bf)

## 1.0.2 - 2015-05-04

### Commits
- [LPS-55043] remove unused (8600faa31a)

## 1.0.1 - 2015-05-04

### Commits
- [LPS-51081] Increment to prepeare for next versions (71f784ba94)
- [LPS-51081] Use com.liferay.gradle.util 1.0.1 release (86c44a6095)
- [LPS-51081] Move configuration into task (5f4cc117ac)
- [LPS-51081] Use Gradle utilities (be5dfc70dc)
- [LPS-51081] Rename (0285936b82)

[COMMERCE-4584]: https://issues.liferay.com/browse/COMMERCE-4584
[LPS-51081]: https://issues.liferay.com/browse/LPS-51081
[LPS-51801]: https://issues.liferay.com/browse/LPS-51801
[LPS-55043]: https://issues.liferay.com/browse/LPS-55043
[LPS-55187]: https://issues.liferay.com/browse/LPS-55187
[LPS-55608]: https://issues.liferay.com/browse/LPS-55608
[LPS-56469]: https://issues.liferay.com/browse/LPS-56469
[LPS-58330]: https://issues.liferay.com/browse/LPS-58330
[LPS-58467]: https://issues.liferay.com/browse/LPS-58467
[LPS-59564]: https://issues.liferay.com/browse/LPS-59564
[LPS-60310]: https://issues.liferay.com/browse/LPS-60310
[LPS-61088]: https://issues.liferay.com/browse/LPS-61088
[LPS-61099]: https://issues.liferay.com/browse/LPS-61099
[LPS-61420]: https://issues.liferay.com/browse/LPS-61420
[LPS-62883]: https://issues.liferay.com/browse/LPS-62883
[LPS-63943]: https://issues.liferay.com/browse/LPS-63943
[LPS-64021]: https://issues.liferay.com/browse/LPS-64021
[LPS-64816]: https://issues.liferay.com/browse/LPS-64816
[LPS-65086]: https://issues.liferay.com/browse/LPS-65086
[LPS-65749]: https://issues.liferay.com/browse/LPS-65749
[LPS-66064]: https://issues.liferay.com/browse/LPS-66064
[LPS-66709]: https://issues.liferay.com/browse/LPS-66709
[LPS-67658]: https://issues.liferay.com/browse/LPS-67658
[LPS-68231]: https://issues.liferay.com/browse/LPS-68231
[LPS-69259]: https://issues.liferay.com/browse/LPS-69259
[LPS-69271]: https://issues.liferay.com/browse/LPS-69271
[LPS-70060]: https://issues.liferay.com/browse/LPS-70060
[LPS-70677]: https://issues.liferay.com/browse/LPS-70677
[LPS-71117]: https://issues.liferay.com/browse/LPS-71117
[LPS-72914]: https://issues.liferay.com/browse/LPS-72914
[LPS-73584]: https://issues.liferay.com/browse/LPS-73584
[LPS-73855]: https://issues.liferay.com/browse/LPS-73855
[LPS-73967]: https://issues.liferay.com/browse/LPS-73967
[LPS-76644]: https://issues.liferay.com/browse/LPS-76644
[LPS-77425]: https://issues.liferay.com/browse/LPS-77425
[LPS-78772]: https://issues.liferay.com/browse/LPS-78772
[LPS-79799]: https://issues.liferay.com/browse/LPS-79799
[LPS-82128]: https://issues.liferay.com/browse/LPS-82128
[LPS-84094]: https://issues.liferay.com/browse/LPS-84094
[LPS-84119]: https://issues.liferay.com/browse/LPS-84119
[LPS-85609]: https://issues.liferay.com/browse/LPS-85609
[LPS-86406]: https://issues.liferay.com/browse/LPS-86406
[LPS-86589]: https://issues.liferay.com/browse/LPS-86589
[LPS-87192]: https://issues.liferay.com/browse/LPS-87192
[LPS-87466]: https://issues.liferay.com/browse/LPS-87466
[LPS-88645]: https://issues.liferay.com/browse/LPS-88645
[LPS-96247]: https://issues.liferay.com/browse/LPS-96247
[LPS-100515]: https://issues.liferay.com/browse/LPS-100515
[LPS-101089]: https://issues.liferay.com/browse/LPS-101089
[LPS-102700]: https://issues.liferay.com/browse/LPS-102700
[LPS-105247]: https://issues.liferay.com/browse/LPS-105247
[LPS-105380]: https://issues.liferay.com/browse/LPS-105380
[LPS-106149]: https://issues.liferay.com/browse/LPS-106149
[LPS-106167]: https://issues.liferay.com/browse/LPS-106167
[LPS-107008]: https://issues.liferay.com/browse/LPS-107008
[LPS-108328]: https://issues.liferay.com/browse/LPS-108328
[LPS-110283]: https://issues.liferay.com/browse/LPS-110283
[LPS-110422]: https://issues.liferay.com/browse/LPS-110422
[LPS-111291]: https://issues.liferay.com/browse/LPS-111291
[LPS-111896]: https://issues.liferay.com/browse/LPS-111896
[LPS-113624]: https://issues.liferay.com/browse/LPS-113624
[LPS-115020]: https://issues.liferay.com/browse/LPS-115020
[LRDOCS-4319]: https://issues.liferay.com/browse/LRDOCS-4319