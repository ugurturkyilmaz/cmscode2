# Liferay Gradle Plugins Patcher Change Log

## 1.0.20 - 2021-04-07

### Commits
- [LPS-130119] Fix patch task (8492e06409)

## 1.0.19 - 2020-11-02

### Commits
- [LPS-111291] Import statements (d414bad0fa)
- [LPS-111291] Gradle 5.6.4 tests (40f4f9e2f3)
- [LPS-111291] Update plugins Gradle version (003c3832b0)

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

## 1.0.18 - 2020-03-04

### Commits
- [LPS-106149] Baseline (becb322fa3)
- [LPS-106149] Cacheable tasks (5f1911b5ba)

### Dependencies
- [LPS-106149] Update the com.liferay.gradle.util dependency to version 1.0.35.

## 1.0.17 - 2020-01-17

### Commits
- [LPS-107313] Update base url to use https (44b834a08a)
- [LPS-100515] Update plugins Gradle version (448efac158)
- [LPS-84119] Create new var for better readability (753a5f0426)
- [LPS-85609] Simplify gradleTest (a8b0feff31)
- [LPS-85609] Use Gradle 4.10.2 (9aa90f8961)

### Dependencies
- [LPS-96247] Update the com.liferay.gradle.util dependency to version 1.0.34.

## 1.0.16 - 2018-11-19

### Dependencies
- [LPS-87466] Update the com.liferay.gradle.util dependency to version 1.0.33.

## 1.0.15 - 2018-11-16

### Commits
- [LPS-87192] Set the Eclipse task property gradleVersion (040b2abdee)
- [LPS-87192] Add variable gradleVersion (no logic changes) (2f7c0b2fe4)
- [LPS-85609] Fix for CI (test only 4.10.2) (4eed005731)
- [LPS-85609] Test plugins up to Gradle 4.10.2 (60905bc960)
- [LPS-86589] Test Gradle plugins from Gradle 2.14.1 to 3.5.1 (6df521a506)
- [LPS-71117] Test plugins with Gradle up to 3.5.1 (c3e12d1cf3)

### Dependencies
- [LPS-87466] Update the com.liferay.gradle.util dependency to version 1.0.32.

## 1.0.14 - 2018-09-17

### Commits
- [LPS-51081] sort (72e111ea5d)
- [LPS-51081] Fix gradleTest for windows (cd23e7aa34)

### Dependencies
- [LPS-84094] Update the com.liferay.gradle.util dependency to version 1.0.31.
- [LPS-84094] Update the com.liferay.gradle.util dependency to version 1.0.30.

### Description
- [LPS-51081] Fix error when running the `patch` task on Windows.

## 1.0.13 - 2018-06-08

### Commits
- [LPS-82178] Enforce semantic versioning (1610ab57c2)
- [LPS-82178] Use "compileClasspath" configuration if it exists (c3e739a09c)
- [LPS-77425] Partial revert of d25f48516a9ad080bcbd50e228979853d3f2dda5
(60d3a950d6)
- [LPS-77425] Increment all major versions (d25f48516a)
- [LPS-70677] No need to look into the local Maven repository during testing
(452be84220)
- [LPS-70060] Test plugins with Gradle 3.3 (09bed59a42)
- [LPS-69259] Test plugins with Gradle 3.2.1 (72873ed836)
- [LPS-69259] Test plugins with Gradle 3.2 (dec6105d3d)
- [LPS-68231] Test plugins with Gradle 3.1 (49ec4cdbd8)
- [LPS-67352] SF, enforce empty line after finishing referencing variable
(4ff2bb6038)
- [LPS-67658] Convert gradle-plugins-patcher sample into a smoke test
(fcb76a161e)
- [LPS-67658] Configure GradleTest in gradle-plugins-patcher (920d1eba15)
- [LPS-67658] Need "compileOnly" to keep dependencies out of "compile"
(4a3cd0bc9d)
- [LPS-67658] These plugins must work with Gradle 2.5+ (5b963e363d)

### Dependencies
- [LPS-77425] Update the com.liferay.gradle.util dependency to version 1.0.29.
- [LPS-73584] Update the com.liferay.gradle.util dependency to version 1.0.29.
- [LPS-73584] Update the com.liferay.gradle.util dependency to version 1.0.28.
- [LPS-72914] Update the com.liferay.gradle.util dependency to version 1.0.27.

### Description
- [LPS-82178] Fix error when adding the original library to the `compileOnly`
configuration.

## 1.0.12 - 2016-08-05

### Commits
- [LPS-67495] Sort (06b5ad5329)
- [LPS-67495] Convert src and patch files to LF before patching (08b3bc5999)
- [LPS-67495] Sort (3f722b2c8c)
- [LPS-67495] No need to patch with "--binary" (9c73b8aaab)

## 1.0.11 - 2016-06-16

### Commits
- [LPS-65749] This method does not throw any checked exception (c4954f5197)
- [LPS-65749] No need to use closures here (749344ac88)
- [LPS-64816] Update Gradle plugin samples (3331002e5d)
- [LPS-61099] Delete build.xml in modules (c9a7e1d370)
- [LPS-63943] This is done automatically now (f1e42382d9)
- [LPS-62883] Update gradle-plugins/build.gradle (20fc2457e6)
- [LPS-61848] An empty settings.gradle is enough (2e5eb90e23)
- [LPS-61088] Remove classes and resources dir from Include-Resource
(1b0e1275bc)

### Dependencies
- [LPS-65749] Update the com.liferay.gradle.util dependency to version 1.0.26.
- [LPS-65086] Update the com.liferay.gradle.util dependency to version 1.0.25.

## 1.0.10 - 2015-12-17

### Commits
- [LPS-61445] Avoid creating .orig files if patches don't match perfectly
(dd17128b5c)
- [LPS-61445] Allow custom args for the "patch" command (bd5e25bab5)
- [LPS-61445] Fix sample (8cd039f4d5)
- [LPS-61445] We assume "java" is applied, so we should apply it first
(a7005a5acd)

## 1.0.9 - 2015-11-18

### Commits
- [LRQA-19302] Show "patch" output even in case of error (c19d7372b1)
- [LRQA-19302] Allow custom original lib file, not only from a configuration
(dbcd1fd9d0)

## 1.0.7 - 2015-11-03

### Dependencies
- [LPS-60153] Update the com.liferay.gradle.util dependency to version 1.0.23.

## 1.0.6 - 2015-10-27

### Commits
- [LPS-59581] destinationDir is set later, so we can't get it now (f18259853e)
- [LPS-59851] Make the original lib src file configurable (3b951113a9)
- [LPS-59851] Simplify (6512372f23)
- [LPS-59564] Update directory layout for "sdk" modules (ea19635556)

## 1.0.5 - 2015-09-10

### Commits
- [LPS-58542] Sort patch files before applying them (c67e31ebd5)
- [LPS-51081] Remove modules' Eclipse project files (b3f19f9012)
- [LPS-51081] Replace modules' Ant files with Gradle alternatives (9e60160a85)
- [LPS-51081] Remove modules' Ivy files (076b384eef)

### Dependencies
- [LPS-58467] Update the com.liferay.gradle.util dependency to version 1.0.19.
- [LPS-51081] Update the com.liferay.gradle.util dependency to version 1.0.18.
- [LPS-51081] Update the com.liferay.gradle.util dependency to version 1.0.17.
- [LPS-51081] Update the com.liferay.gradle.util dependency to version 1.0.16.

## 1.0.4 - 2015-07-31

### Commits
- [LPS-51081] Make the URL to retrieve the lib source files configurable
(d53d875924)

### Dependencies
- [LPS-51081] Update the com.liferay.gradle.util dependency to version 1.0.15.

## 1.0.3 - 2015-07-27

### Commits
- [LPS-51081] Update to Gradle 2.5 (3aa4c1f053)

### Dependencies
- [LPS-51081] Update the com.liferay.gradle.util dependency to version 1.0.14.

## 1.0.2 - 2015-07-02

### Commits
- [LPS-51081] Simplify (554676f3c5)
- [LPS-51081] andrea, please fix (6b51d1c909)
- [LPS-51081] Consistency (53d74f708d)
- [LPS-51801] Use Gradle API to grab dependencies from the local Gradle
installation (48f775db44)
- [LPS-51081] Ran "ant reset-gradle init-gradle" (9ab363b842)
- [LPS-51081] Move logic to util class (48460ccfe7)

### Dependencies
- [LPS-51081] Update the com.liferay.gradle.util dependency to version 1.0.13.
- [LPS-51081] Update the com.liferay.gradle.util dependency to version 1.0.12.
- [LPS-51081] Update the com.liferay.gradle.util dependency to version 1.0.11.
- [LPS-51081] Update the org.gradle.gradle-base-services dependency to version
2.4.
- [LPS-51081] Update the org.gradle.gradle-base-services-groovy dependency to
version 2.4.
- [LPS-51081] Update the org.gradle.gradle-core dependency to version 2.4.
- [LPS-51081] Update the org.gradle.gradle-language-java dependency to version
2.4.
- [LPS-51081] Update the org.gradle.gradle-language-jvm dependency to version
2.4.
- [LPS-51081] Update the org.gradle.gradle-plugins dependency to version 2.4.
- [LPS-51081] Update the ant dependency to version 1.9.4.
- [LPS-51081] Update the groovy-all dependency to version 2.3.10.

## 1.0.1 - 2015-06-25

### Commits
- [LPS-51081] Use new overload (1ac5fc6c66)
- [LPS-51081] Fix line returns on "patch" logs (b1b47b6287)
- [LPS-51081] Fix patches in Windows (2e3949244b)
- [LPS-51081] Better sample (e3b96fab6c)
- [LPS-51081] Force Unix line endings to avoid patch errors (2456719303)

[LPS-51081]: https://issues.liferay.com/browse/LPS-51081
[LPS-51801]: https://issues.liferay.com/browse/LPS-51801
[LPS-58467]: https://issues.liferay.com/browse/LPS-58467
[LPS-58542]: https://issues.liferay.com/browse/LPS-58542
[LPS-59564]: https://issues.liferay.com/browse/LPS-59564
[LPS-59581]: https://issues.liferay.com/browse/LPS-59581
[LPS-59851]: https://issues.liferay.com/browse/LPS-59851
[LPS-60153]: https://issues.liferay.com/browse/LPS-60153
[LPS-61088]: https://issues.liferay.com/browse/LPS-61088
[LPS-61099]: https://issues.liferay.com/browse/LPS-61099
[LPS-61445]: https://issues.liferay.com/browse/LPS-61445
[LPS-61848]: https://issues.liferay.com/browse/LPS-61848
[LPS-62883]: https://issues.liferay.com/browse/LPS-62883
[LPS-63943]: https://issues.liferay.com/browse/LPS-63943
[LPS-64816]: https://issues.liferay.com/browse/LPS-64816
[LPS-65086]: https://issues.liferay.com/browse/LPS-65086
[LPS-65749]: https://issues.liferay.com/browse/LPS-65749
[LPS-67352]: https://issues.liferay.com/browse/LPS-67352
[LPS-67495]: https://issues.liferay.com/browse/LPS-67495
[LPS-67658]: https://issues.liferay.com/browse/LPS-67658
[LPS-68231]: https://issues.liferay.com/browse/LPS-68231
[LPS-69259]: https://issues.liferay.com/browse/LPS-69259
[LPS-70060]: https://issues.liferay.com/browse/LPS-70060
[LPS-70677]: https://issues.liferay.com/browse/LPS-70677
[LPS-71117]: https://issues.liferay.com/browse/LPS-71117
[LPS-72914]: https://issues.liferay.com/browse/LPS-72914
[LPS-73584]: https://issues.liferay.com/browse/LPS-73584
[LPS-77425]: https://issues.liferay.com/browse/LPS-77425
[LPS-82178]: https://issues.liferay.com/browse/LPS-82178
[LPS-84094]: https://issues.liferay.com/browse/LPS-84094
[LPS-84119]: https://issues.liferay.com/browse/LPS-84119
[LPS-85609]: https://issues.liferay.com/browse/LPS-85609
[LPS-86589]: https://issues.liferay.com/browse/LPS-86589
[LPS-87192]: https://issues.liferay.com/browse/LPS-87192
[LPS-87466]: https://issues.liferay.com/browse/LPS-87466
[LPS-88645]: https://issues.liferay.com/browse/LPS-88645
[LPS-96247]: https://issues.liferay.com/browse/LPS-96247
[LPS-100515]: https://issues.liferay.com/browse/LPS-100515
[LPS-106149]: https://issues.liferay.com/browse/LPS-106149
[LPS-107313]: https://issues.liferay.com/browse/LPS-107313
[LPS-110283]: https://issues.liferay.com/browse/LPS-110283
[LPS-110422]: https://issues.liferay.com/browse/LPS-110422
[LPS-111291]: https://issues.liferay.com/browse/LPS-111291
[LPS-111896]: https://issues.liferay.com/browse/LPS-111896
[LPS-113624]: https://issues.liferay.com/browse/LPS-113624
[LPS-115020]: https://issues.liferay.com/browse/LPS-115020
[LPS-130119]: https://issues.liferay.com/browse/LPS-130119
[LRQA-19302]: https://issues.liferay.com/browse/LRQA-19302