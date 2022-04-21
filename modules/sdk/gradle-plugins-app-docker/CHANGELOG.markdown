# Liferay Gradle Plugins App Docker Change Log

## 1.0.12 - 2020-11-02

### Commits
- [LPS-111291] Import statements (d414bad0fa)
- [LPS-111291] Update readme (a87d2593e9)
- [LPS-111291] Gradle 5.6.4 tests (40f4f9e2f3)
- [LPS-111291] Update README.markdown (eea12b7f8f)
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

## 1.0.11 - 2020-03-04

### Commits
- [LPS-105380] Rename exception variables (b3173da81b)
- [LPS-100515] Update README.markdown (694b3791de)
- [LPS-100515] Update plugins Gradle version (448efac158)

### Dependencies
- [LPS-106149] Update the com.liferay.gradle.util dependency to version 1.0.35.

## 1.0.10 - 2019-11-19

### Commits
- [LPS-100515] Check .m2-tmp repository (7e54299419)
- [LPS-100515] The task name must not contain a colon or forward slash
(202125c648)
- [LPS-84119] Do not declare var (85dc5fdf91)
- [LPS-85609] Update readme (c182ff396d)
- [LPS-85609] Simplify gradleTest (a8b0feff31)
- [LPS-86806] Fix line breaks in return statements (29ae0ec415)
- [LPS-85609] Use Gradle 4.10.2 (9aa90f8961)

### Dependencies
- [LPS-96247] Update the com.liferay.gradle.util dependency to version 1.0.34.

## 1.0.9 - 2018-11-19

### Dependencies
- [LPS-87466] Update the com.liferay.gradle.util dependency to version 1.0.33.

## 1.0.8 - 2018-11-16

### Commits
- [LPS-87192] Set the Eclipse task property gradleVersion (040b2abdee)
- [LPS-87192] Add variable gradleVersion (no logic changes) (2f7c0b2fe4)
- [LPS-85609] Fix for CI (test only 4.10.2) (4eed005731)
- [LPS-85609] Test plugins up to Gradle 4.10.2 (60905bc960)
- [LPS-85609] Update supported Gradle versions (d79b89682b)

### Dependencies
- [LPS-87466] Update the com.liferay.gradle.util dependency to version 1.0.32.

## 1.0.7 - 2018-11-01

### Commits
- [LPS-86875] update gradle-docker-plugin to 3.6.2 (ca4517a231)

### Dependencies
- [LPS-86875] Update the gradle-docker-plugin dependency to version 3.6.2.

## 1.0.6 - 2018-10-22

### Commits
- [LPS-86588] Upgrade gradle-docker-plugin to 3.6.1 for Gradle 4.10.2
(3afdd4d33b)
- [LPS-86589] Update readme (4280a3d596)
- [LPS-86589] Test Gradle plugins from Gradle 2.14.1 to 3.5.1 (6df521a506)
- [LPS-71117] Test plugins with Gradle up to 3.5.1 (c3e12d1cf3)
- [LPS-71117] Update supported Gradle versions in READMEs (fdcc16c0d4)

### Dependencies
- [LPS-86588] Update the gradle-docker-plugin dependency to version 3.6.1.

## 1.0.5 - 2018-08-30

### Commits
- [LPS-77425] Partial revert of d25f48516a9ad080bcbd50e228979853d3f2dda5
(60d3a950d6)
- [LPS-77425] Increment all major versions (d25f48516a)
- [LPS-76644] Enable Gradle plugins publishing (8bfdfd53d7)
- [LPS-76644] Add description to Gradle plugins (5cb7b30e6f)
- [LPS-66709] Fix readme (5d383a2929)

### Dependencies
- [LPS-84094] Update the com.liferay.gradle.util dependency to version 1.0.31.
- [LPS-84094] Update the com.liferay.gradle.util dependency to version 1.0.30.
- [LPS-77425] Update the com.liferay.gradle.util dependency to version 1.0.29.

## 1.0.4 - 2017-11-03

### Dependencies
- [LPS-75704] Update the gradle-docker-plugin dependency to version 3.2.0.

## 1.0.3 - 2017-10-18

### Commits
- [LPS-75327] Automatically fix line endings of .sh files (82ee1acba1)

### Description
- [LPS-75327] Automatically convert `.sh` files to Unix-style line endings when
building the app's Docker image.

## 1.0.2 - 2017-09-20

### Commits
- [LPS-74811] Add Gradle test (3eb7288f0f)
- [LPS-74811] Copy the .war file of WAR projects (d94d503c01)

### Description
- [LPS-74811] Include the WAR file of WAR projects in the Docker image.

## 1.0.1 - 2017-09-19

### Commits
- [LPS-74785] Avoid failing the build if Git throws an error (43aeb3737d)

### Dependencies
- [LPS-73584] Update the com.liferay.gradle.util dependency to version 1.0.29.
- [LPS-73584] Update the com.liferay.gradle.util dependency to version 1.0.28.

### Description
- [LPS-74785] Avoid failing the build in the case of a Git error.

[LPS-66709]: https://issues.liferay.com/browse/LPS-66709
[LPS-71117]: https://issues.liferay.com/browse/LPS-71117
[LPS-73584]: https://issues.liferay.com/browse/LPS-73584
[LPS-74785]: https://issues.liferay.com/browse/LPS-74785
[LPS-74811]: https://issues.liferay.com/browse/LPS-74811
[LPS-75327]: https://issues.liferay.com/browse/LPS-75327
[LPS-75704]: https://issues.liferay.com/browse/LPS-75704
[LPS-76644]: https://issues.liferay.com/browse/LPS-76644
[LPS-77425]: https://issues.liferay.com/browse/LPS-77425
[LPS-84094]: https://issues.liferay.com/browse/LPS-84094
[LPS-84119]: https://issues.liferay.com/browse/LPS-84119
[LPS-85609]: https://issues.liferay.com/browse/LPS-85609
[LPS-86588]: https://issues.liferay.com/browse/LPS-86588
[LPS-86589]: https://issues.liferay.com/browse/LPS-86589
[LPS-86806]: https://issues.liferay.com/browse/LPS-86806
[LPS-86875]: https://issues.liferay.com/browse/LPS-86875
[LPS-87192]: https://issues.liferay.com/browse/LPS-87192
[LPS-87466]: https://issues.liferay.com/browse/LPS-87466
[LPS-88645]: https://issues.liferay.com/browse/LPS-88645
[LPS-96247]: https://issues.liferay.com/browse/LPS-96247
[LPS-100515]: https://issues.liferay.com/browse/LPS-100515
[LPS-105380]: https://issues.liferay.com/browse/LPS-105380
[LPS-106149]: https://issues.liferay.com/browse/LPS-106149
[LPS-110283]: https://issues.liferay.com/browse/LPS-110283
[LPS-110422]: https://issues.liferay.com/browse/LPS-110422
[LPS-111291]: https://issues.liferay.com/browse/LPS-111291
[LPS-111896]: https://issues.liferay.com/browse/LPS-111896
[LPS-113624]: https://issues.liferay.com/browse/LPS-113624
[LPS-115020]: https://issues.liferay.com/browse/LPS-115020