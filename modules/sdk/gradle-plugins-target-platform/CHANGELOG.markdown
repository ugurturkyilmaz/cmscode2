# Liferay Gradle Plugins Target Platform Change Log

## 2.1.39 - 2022-01-12

### Commits
- [LPS-105380] prep nex (797e97c019)

### Dependencies
- [LRCI-2670] Update the com.liferay.gradle.util dependency to version 1.0.46.

## 2.1.38 - 2021-12-09

### Commits
- [LPS-142100] [LPS-141250] revert (eee87baefd)

### Dependencies
- [LPS-143280] Update the com.liferay.gradle.util dependency to version 1.0.45.

## 2.1.37 - 2021-10-19

### Commits
- [LPS-105380] Remove final from parameters (a4e6ca0985)
- [LPS-105380] Remove final from variables (5a4e0b26b4)
- [LPS-105380] Revert "LPS-105380 Remove final from variables" (cdda76fc8e)
- [LPS-105380] Revert "LPS-105380 Remove final from parameters" (b2f9eed2c2)
- [LPS-105380] Remove final from parameters (f615ff77ff)
- [LPS-105380] Remove final from variables (ffe6186e71)

## 2.1.33 - 2021-05-18

### Commits
- [LPS-130505] reverted, republish (a1551030f2)

## 2.1.32 - 2021-03-30

### Commits
- [LPS-129874] Source formatting (677945141b)
- [LPS-129874] Manually construct the Bndrun so we can overload the
`getUnexpandedProperty` method so that it exibits the old behaviour of falling
through to the parent processor (8fd4170a3d)

## 2.1.29 - 2021-02-12

### Commits
- [LPS-105380] Make var final (12e010029a)

## 2.1.26 - 2020-11-16

### Commits
- [LPS-123192] Fix tests (a0769d730f)
- [LPS-123192] Fix tests (2efdaffa7b)

## 2.1.25 - 2020-11-02

### Commits
- [LPS-111291] Import statements (d414bad0fa)
- [LPS-111291] Update readme (a87d2593e9)
- [LPS-111291] Gradle 5.6.4 tests (40f4f9e2f3)
- [LPS-111291] Update README.markdown (eea12b7f8f)
- [LPS-111291] Update plugins Gradle version (003c3832b0)
- [LPS-121824] force referesh maven (0fe31dcef7)
- [LPS-105380] Methods don't need to be static (c42da838cc)

### Dependencies
- [LPS-111291] Update the com.liferay.gradle.util dependency to version 1.0.44.

## 2.1.23 - 2020-07-06

### Commits
- [LPS-111461] fix excludes (551c8279b4)

## 2.1.18 - 2020-06-11

### Commits
- [LPS-111461] exclude gradle-util (9839838e5a)
- [LPS-105380] Rename variable: obj -> object (64cb899b48)
- [LPS-113024] [LPS-114570] update because of CI CDN issues (8a163df22c)

### Dependencies
- [LPS-115020] Update the com.liferay.gradle.util dependency to version 1.0.43.

## 2.1.17 - 2020-06-04

### Commits
- [LPS-114882] Add providedModules to whitelist (00cde5380b)

## 2.1.11 - 2020-05-20

### Dependencies
- [LPS-88645] Update the com.liferay.gradle.util dependency to version 1.0.42.

## 2.1.9 - 2020-05-13

### Commits
- [LPS-105380] revert (c22fc8b3e7)

### Dependencies
- [LPS-88645] Update the com.liferay.gradle.util dependency to version 1.0.41.
- [LPS-113624] Update the com.liferay.gradle.util dependency to version 1.0.40.

## 2.1.5 - 2020-04-28

### Commits
- [LPS-112500] Add jsCompile to whitelist for TP configurations (79afe309f9)
- [LPS-98417] Revert "LPS-98417 Add jsCompile to whitelist for TP
configurations" (37bcf0a44a)

### Dependencies
- [LPS-110422] Update the com.liferay.gradle.util dependency to version 1.0.39.

## 2.1.4 - 2020-04-22

### Commits
- [LPS-98417] Add jsCompile to whitelist for TP configurations (91020459c6)

### Dependencies
- [LPS-111896] Update the com.liferay.gradle.util dependency to version 1.0.38.
- [LPS-88645] Update the com.liferay.gradle.util dependency to version 1.0.37.
- [LPS-110283] Update the com.liferay.gradle.util dependency to version 1.0.36.

## 2.1.3 - 2020-03-04

### Dependencies
- [LPS-106149] Update the com.liferay.gradle.util dependency to version 1.0.35.

## 2.1.1 - 2020-02-20

### Commits
- [LPS-107155] normalize test files (730329504d)
- [LPS-107155] update test in order to ensure it works with subprojects
(0f2a56e61a)
- [LPS-107155] ensure task are never up2date (8523616643)
- [LPS-107155] add dependencyManagement task to only root project (e121b130c8)
- [LPS-105380] Rename var/parameter in constructor in order to match
(480529fe98)
- [LPS-107155] Buy space (2d7c2bad92)

## 2.1.0 - 2020-01-27

### Commits
- [LPS-107155] Baseline (a6e47e6926)

## 2.0.9 - 2020-01-27

### Commits
- [LPS-107155] semver (b11529bc71)
- [LPS-107155] Sort alphabetically (ac17b7bc90)
- [LPS-107155] Update description message (c03028e705)
- [LPS-107155] Update log message (015778ec70)
- [LPS-107155] As used (527a0e99fb)
- [LPS-107155] Inline (ba294e048f)
- [LPS-107155] Remove empty log message (802db0feb4)
- [LPS-107155] Simplify parameters (2bd4e15cba)
- [LPS-107155] Use list (b87569ce45)
- [LPS-107155] Update exception message (7e946c4f47)
- [LPS-107155] As used (a8ef8d13e9)
- [LPS-107155] Rename variable (e799960ab1)
- [LPS-107155] Simplify dependencyManagement task to text output only
(cbf7138a44)
- [LPS-107155] Variable names (2e38d0181a)
- [LPS-107155] Move comparator logic (edf61a52e4)
- [LPS-107155] Use console mode plain (8c7427e792)
- [LPS-107155] Wrap it like a burrito (a2eeb3aafb)
- [LPS-107155] Inline (d92420cd35)
- [LPS-107155] Remove unneeded line break (3552599562)
- [LPS-107155] Remove unneeded final keyword (6319e914e8)
- [LPS-107155] Update task description (bfe6162b1f)
- [LPS-107155] Update exception message (a82af282ce)
- [LPS-107155] fix test (17276efc5f)
- [LPS-107155] refactor to reduce public API (0351bce6a0)
- [LPS-107155] show that plugin now requires gradle 5.x (7a27a3415e)
- [LPS-107155] refactor as a single test project (b0cffecf1b)
- [LPS-107155] use gradle idiomatic APIs (bea8958646)
- [LPS-107155] show errors in build lifecycle (595c063d11)
- [LPS-107155] Use standard java APIs (d865302771)
- [LPS-107155] use standard json dependency (3f96db66b2)
- [LPS-107155] add dependencyManagement task to get all target platform
dependencies (78d2606f6b)
- [LPS-105380] Rename exception variables (b3173da81b)

### Dependencies
- [LPS-107155] Update the json dependency to version 20180813.
- [LPS-107155] Update the fastjson dependency to version 1.2.62.
- [LPS-107155] Update the commons-io dependency to version 2.6.
- [LPS-107155] Update the dom4j dependency to version 2.1.1.

## 2.0.8 - 2020-01-10

### Commits
- [LPS-105889] Update version (2d18117cbc)
- [LPS-105889] Move logic to extension (cf7169331f)
- [LPS-105889] Consistency (4dd3685e3b)
- [LPS-105889] Sort alphabetically (fb5b983e7c)
- [LPS-105889] simplify tests by removing workspace plugin (45f72dbd20)
- [LPS-105889] add test case to make sure that configurations with
defaultDependencies are not broken (c67cf08b21)
- [LPS-105889] remove deprecation since this is not needed again (062bc3de66)
- [LPS-105889] add back whitelist of configurations to apply platform dependency
(ee749e80fb)
- [LPS-102243] Partial revert (b5ee7e9c16)
- [LPS-102243] Remove portal-test-integration and all references to it from
build and classpath (c2efa6c7f2)

## 2.0.7 - 2019-12-24

### Commits
- [LPS-105502] Use final when the variable is used inside a closure (f59ebe3998)
- [LPS-105502] Sort (fb937e7b1a)
- [LPS-105502] Rename method (35cfb8fe71)
- [LPS-105502] Use char (a6f8643cbf)
- [LPS-105502] Add logging (421b88559e)
- [LPS-105502] Sort closures alphabetically (5cdaf1b90d)
- [LPS-105889] Rename (119e7cc77b)
- [LPS-105889] Gradle 5 BOM platform only works for transitive configurations
(a11ab82f1e)
- [LPS-105889] add deprecation notice (b01f914374)
- [LPS-105889] update tests to new gradle5 BOM behavior (675b006f27)
- [LPS-105889] Speed up ide model tests (6a0a2e3860)
- [LPS-105889] Remove asserts that required old spring API (e5c5455ed8)
- [LPS-105889] Remove unnecessary test (d1049fbb60)
- [LPS-105889] Use published BOMs instead (f1d6547ec5)
- [LPS-105889] remove eclipse warnings (8c93363308)
- [LPS-105889] update targetPlatformIDE configuration to use gradle5 BOM support
(f21c5bcc03)
- [LPS-105889] use native gradle 5 BOM support instead of spring
dependency-management-plugin (4283a850ef)

### Description
- [LPS-105889] Use native Gradle 5 BOM support for target platform capability.

## 2.0.6 - 2019-11-27

### Commits
- [LPS-100515] Update README.markdown (694b3791de)
- [LPS-100515] Update plugins Gradle version (448efac158)

## 2.0.5 - 2019-11-15

### Commits
- [LPS-103466] Sort (5b5ff8dcca)
- [LPS-103809] preop next (3768e9a4b7)
- [LPS-103466] revert (e4f42e7a25)
- [LPS-103466] Revert "LPS-103466 Sort" (fffa7e7259)
- [LPS-103466] Sort (1cd1d09425)

## 2.0.4 - 2019-10-21

### Commits
- [LPS-95938] Use bnd 4.3.0 in workspace and target platform (60bc0adb96)
- [LPS-103051] temp rollback (9a1e210294)

### Dependencies
- [LPS-95938] Update the biz.aQute.bnd dependency to version 4.3.0.
- [LPS-95938] Update the biz.aQute.bnd.gradle dependency to version 4.3.0.
- [LPS-95938] Update the biz.aQute.repository dependency to version 4.3.0.
- [LPS-95938] Update the biz.aQute.resolve dependency to version 4.3.0.

## 2.0.3 - 2019-09-23

### Commits
- [LPS-100448] fix tests (e363c9e9b6)

## 2.0.2 - 2019-08-27

### Commits
- [LPS-100448] Auto-SF (0ff1cd4057)
- [LPS-99577] fix classpath problem in tests (13be6aecd6)
- [LPS-93483] try 2.1.1? (9b15f81863)
- [LPS-98877] [LPS-96095] auto SF for portlet-api (fc1fff6de9)

## 2.0.1 - 2019-07-30

### Commits
- [LPS-98190] fix test (ac25d5af38)
- [LPS-98190] Fix classpath (e938e3099f)
- [LPS-98190] fix test classpath (af3c278aab)
- [LPS-84119] Avoid chaining on method 'stream' (188b859489)
- [LPS-97601] Follow existing patterns (3da8ac438d)
- [LPS-97601] fix classpath issues in tests (e8db32ee57)
- [LPS-96290] fix tests (caebcf052f)
- [LPS-84119] Use 'osgi.core' instead of 'org.osgi.core' (01606b6fb1)

### Dependencies
- [LPS-96247] Update the com.liferay.gradle.util dependency to version 1.0.34.

## 2.0.0 - 2019-05-20

### Commits
- [LPS-95279] Fix whitespace (3bfa528f9e)
- [LPS-95279] Grammar (ae6f4a544c)
- [LPS-95279] Code consistency with other Gradle plugins (no logic changes)
(ceeb405edf)
- [LPS-95279] Check logging level (54fb914592)
- [LPS-95279] Sort alphabetically (e183cb2742)
- [LPS-95279] Remove duplicate code (7d47a0c18f)
- [LPS-95279] Use final when the variable is used inside a closure (f3f1d7efab)
- [LPS-95279] More descriptive constant name (7446fdf338)
- [LPS-95279] refactor and update tests according to new target-platform plugin
behavior (a5834de9c6)
- [LPS-95279] add runbundles to accessible API (0b8cc7f9d2)
- [LPS-95279] if we have a single project, add resolve task if applicable
(64ddc6fcab)
- [LPS-95279] only apply this plugin to javaPlugin projects (97c8ad0f40)
- [LPS-95279] wrapping (b2ea2b2358)
- [LPS-95279] not needed (9d514e7d66)
- [LPS-95279] move all of our predicates to extension object (d73449d1df)
- [LPS-95279] don't add resolve if osgi plugin hasn't been applied (d3b8cb36b4)
- [LPS-95279] only apply plugin to subproject is it passes spec (8510f880be)
- [LPS-95279] don't add and configure resolveTask to root project (94f9c7a729)
- [LPS-95279] fix up the resolve task (1f219430c4)
- [LPS-95279] more resolve task work (9a86b91941)
- [LPS-95279] refactor ResolveTask (68f5442f97)
- [LPS-95279] var name (edb5a8cdc3)
- [LPS-95279] fix conflict between target platform and user configured
maven-publish plugin (f367d6b48e)
- [LPS-95279] fix tests (00530d6a67)

### Dependencies
- [LPS-95279] Update the biz.aQute.bnd dependency to version 4.2.0.
- [LPS-95279] Update the biz.aQute.bnd.gradle dependency to version 4.2.0.
- [LPS-95279] Update the biz.aQute.repository dependency to version 4.2.0.
- [LPS-95279] Update the biz.aQute.resolve dependency to version 4.2.0.
- [LPS-95279] Update the biz.aQute.bnd.gradle dependency to version 3.5.0.

## 1.1.14 - 2019-05-20

### Commits
- [LPS-95279] semver (c368d050fb)
- [LPS-95279] refactor targetPlatformIDE extension, remove includedGroups and
add indexSources (9b296b8d83)

## 1.1.12 - 2019-04-15

### Commits
- [LPS-93873] update dependency-management-plugin version (275e8ed5db)
- [LPS-91772] this is a publish of a new SF to revert the old one (23537d6e71)
- [LPS-91342] regen (334a31a0b6)
- [LPS-91342] regen (a492452b39)

### Dependencies
- [LPS-93873] Update the dependency-management-plugin dependency to version
1.0.7.RELEASE.

### Description
- [LPS-93873] Update the `dependency-management-plugin` to `1.0.7.RELEASE`.

## 1.1.9 - 2019-01-08

### Commits
- [LPS-85609] Update readme (c182ff396d)
- [LPS-85609] Simplify gradleTest (a8b0feff31)

## 1.1.8 - 2019-01-02

### Commits
- [LPS-88382] release temp revert (414be58373)

## 1.1.6 - 2018-11-20

### Commits
- [LPS-87419] Add optional m2-tmp repo (34cda4b600)
- [LPS-85609] Use Gradle 4.10.2 (9aa90f8961)

## 1.1.5 - 2018-11-19

### Dependencies
- [LPS-87466] Update the com.liferay.gradle.util dependency to version 1.0.33.

## 1.1.4 - 2018-11-16

### Commits
- [LPS-87192] Set the Eclipse task property gradleVersion (040b2abdee)
- [LPS-87192] Add variable gradleVersion (no logic changes) (2f7c0b2fe4)
- [LPS-85609] Fix for CI (test only 4.10.2) (4eed005731)
- [LPS-85609] Test plugins up to Gradle 4.10.2 (60905bc960)
- [LPS-85609] Update supported Gradle versions (d79b89682b)
- [LPS-86916] Regen (2bd81691d0)
- [LPS-85609] Fix for Gradle 4.0.2 (801a24514a)
- [LPS-86589] Update readme (4280a3d596)
- [LPS-86581] Add optional m2-tmp repo to resolve the latest
com.liferay.gradle.plugins (b389243ac9)

### Dependencies
- [LPS-87466] Update the com.liferay.gradle.util dependency to version 1.0.32.

## 1.1.3 - 2018-10-09

### Commits
- [LPS-85946] add support for ThemeBuilder related configurations (c82f026055)
- [LPS-85264] rename var (2745afb9fe)
- [LPS-85264] Target platform plugin should support ext modules (03b5d5ffcd)
- [LPS-71117] Test plugins with Gradle up to 3.5.1 (c3e12d1cf3)
- [LPS-71117] Update supported Gradle versions in READMEs (fdcc16c0d4)
- [LPS-82491] Fix gradleTest for Gradle 3.5.1 (0e7dfcf872)
- [LPS-84119] Remove unused methods (74dba76ca9)

### Dependencies
- [LPS-84094] Update the com.liferay.gradle.util dependency to version 1.0.31.
- [LPS-84094] Update the com.liferay.gradle.util dependency to version 1.0.30.

## 1.1.2 - 2018-08-08

### Commits
- [LPS-83922] set applyMavenExclusions = false to improve dependency resolution
(d93d54dc18)
- [LPS-83922] don't add requirements for jars with no BSN (c29b894abe)

### Description
- [LPS-83922] Don't add requirements for files without a BSN.
- [LPS-83922] Set `applyMavenExclusions` to `false` to improve performance.

## 1.1.1 - 2018-07-23

### Commits
- [LPS-82491] Wordsmith (1e5c6ede6a)
- [LPS-82491] update changelog and readme (8a099c414d)
- [LPS-82491] sort (62f640822d)
- [LPS-82491] simplify (d657dad725)
- [LPS-82491] fix test (c8100f4a8d)
- [LPS-82491] fix intellij source set error (4e1614f9ad)
- [LPS-82491] Not needed (16a290a455)
- [LPS-82491] don't add source set, java plugin does that (842ebedd9e)
- [LPS-82491] add test (b1a7bd6728)
- [LPS-82491] variable names (8f39e0921b)
- [LPS-82491] rename (c25ca41279)
- [LPS-82491] sort (0c1296235c)
- [LPS-82491] Portal target platform plugin should support Intellij IDEA
(59fc629083)

### Description
- [LPS-82491] Add support for the `idea` plugin.

## 1.1.0 - 2018-07-17

### Commits
- [LPS-74544] Auto SF (update cdn urls) (e6128bc643)
- [IDE-4081] update gradle-plugins-workspace to 1.10.1 (da694ed736)
- [LPS-77425] Restore version (765863e00e)
- [LPS-81530] Update readme (505b894ae3)
- [LPS-81530] Simplify and allow to pass configurations instead of names
(e07c601a76)

### Description
- [LPS-81530] Add the ability to configure imported BOMs to manage Java
dependencies and the various artifacts used in resolving OSGi dependencies.

## 1.0.2 - 2018-05-29

### Commits
- [LPS-81530] Remove unneeded methods (34eb7a5ff0)
- [LPS-81530] don't save applyToConfiguration Set (26ee244743)
- [LPS-81530] rename (a0b9764d48)
- [LPS-81530] fix tests since the distro.jar has portlet 2.0 api instead
(1a20208a67)
- [LPS-81530] add new extension object method applyToConfiguration (fef625dbf0)
- [LPS-81530] refactor dependency management configuration to util (7431d1d42c)
- [LPS-81042] Use Latest Workspace (5ec5c37cf9)
- [LPS-79653] Portlet 3.0: Upgrade to the Portlet 3.0.0 API (upgrade templates
and unit tests) (045479bf7c)

## 1.0.1 - 2018-05-07

### Commits
- [LPS-80222] Avoid chaining (a8393cdad7)
- [LPS-80222] Avoid using raw types, even in Gradle (9255d9c4e2)
- [LPS-80222] We already know the max size of this list (a5f4505488)
- [LPS-80222] Use constants if available (85079d5c62)
- [LPS-80222] only apply dependency management to specific configurations
(bbff5ece71)
- [LPS-77425] Partial revert of d25f48516a9ad080bcbd50e228979853d3f2dda5
(60d3a950d6)
- [LPS-77425] Partial revert of b739c8fcdc5d1546bd642ca931476c71bbaef1fb
(02ca75b1da)
- [LPS-77425] Increment all major versions (d25f48516a)
- [LPS-77343] Edit Target Platform Gradle plugin Readme (fe25cde2ca)
- [LPS-77343] Wordsmith (0849914aa8)
- [LPS-77343] rough draft of README (b2584fd912)
- [LPS-77343] add additional tests for documented features (2d1030b56a)
- [LPS-77343] switch bndrunFile to input and write to temp dir if not specified
(8882664e48)

### Dependencies
- [LPS-77425] Update the com.liferay.gradle.util dependency to version 1.0.29.
- [LPS-77425] Update the dependency-management-plugin dependency to version
1.0.4.RELEASE.

### Description
- [LPS-77343] Add the ability to use a manually generated bndrun file in a
`ResolveTask` instance by setting the property `bndrunFile` to a valid file.
- [LPS-80222] Apply BOM files only on the following configurations:
	- configuration `compileInclude` added by [Liferay Gradle Plugins]
	- configuration `default`
	- configurations added by [Liferay Gradle Plugins Test Integration]
	- configurations added by the `java` plugin

[IDE-4081]: https://issues.liferay.com/browse/IDE-4081
[LPS-71117]: https://issues.liferay.com/browse/LPS-71117
[LPS-74544]: https://issues.liferay.com/browse/LPS-74544
[LPS-77343]: https://issues.liferay.com/browse/LPS-77343
[LPS-77425]: https://issues.liferay.com/browse/LPS-77425
[LPS-79653]: https://issues.liferay.com/browse/LPS-79653
[LPS-80222]: https://issues.liferay.com/browse/LPS-80222
[LPS-81042]: https://issues.liferay.com/browse/LPS-81042
[LPS-81530]: https://issues.liferay.com/browse/LPS-81530
[LPS-82491]: https://issues.liferay.com/browse/LPS-82491
[LPS-83922]: https://issues.liferay.com/browse/LPS-83922
[LPS-84094]: https://issues.liferay.com/browse/LPS-84094
[LPS-84119]: https://issues.liferay.com/browse/LPS-84119
[LPS-85264]: https://issues.liferay.com/browse/LPS-85264
[LPS-85609]: https://issues.liferay.com/browse/LPS-85609
[LPS-85946]: https://issues.liferay.com/browse/LPS-85946
[LPS-86581]: https://issues.liferay.com/browse/LPS-86581
[LPS-86589]: https://issues.liferay.com/browse/LPS-86589
[LPS-86916]: https://issues.liferay.com/browse/LPS-86916
[LPS-87192]: https://issues.liferay.com/browse/LPS-87192
[LPS-87419]: https://issues.liferay.com/browse/LPS-87419
[LPS-87466]: https://issues.liferay.com/browse/LPS-87466
[LPS-88382]: https://issues.liferay.com/browse/LPS-88382
[LPS-88645]: https://issues.liferay.com/browse/LPS-88645
[LPS-91342]: https://issues.liferay.com/browse/LPS-91342
[LPS-91772]: https://issues.liferay.com/browse/LPS-91772
[LPS-93483]: https://issues.liferay.com/browse/LPS-93483
[LPS-93873]: https://issues.liferay.com/browse/LPS-93873
[LPS-95279]: https://issues.liferay.com/browse/LPS-95279
[LPS-95938]: https://issues.liferay.com/browse/LPS-95938
[LPS-96095]: https://issues.liferay.com/browse/LPS-96095
[LPS-96247]: https://issues.liferay.com/browse/LPS-96247
[LPS-96290]: https://issues.liferay.com/browse/LPS-96290
[LPS-97601]: https://issues.liferay.com/browse/LPS-97601
[LPS-98190]: https://issues.liferay.com/browse/LPS-98190
[LPS-98417]: https://issues.liferay.com/browse/LPS-98417
[LPS-98877]: https://issues.liferay.com/browse/LPS-98877
[LPS-99577]: https://issues.liferay.com/browse/LPS-99577
[LPS-100448]: https://issues.liferay.com/browse/LPS-100448
[LPS-100515]: https://issues.liferay.com/browse/LPS-100515
[LPS-102243]: https://issues.liferay.com/browse/LPS-102243
[LPS-103051]: https://issues.liferay.com/browse/LPS-103051
[LPS-103466]: https://issues.liferay.com/browse/LPS-103466
[LPS-103809]: https://issues.liferay.com/browse/LPS-103809
[LPS-105380]: https://issues.liferay.com/browse/LPS-105380
[LPS-105502]: https://issues.liferay.com/browse/LPS-105502
[LPS-105889]: https://issues.liferay.com/browse/LPS-105889
[LPS-106149]: https://issues.liferay.com/browse/LPS-106149
[LPS-107155]: https://issues.liferay.com/browse/LPS-107155
[LPS-110283]: https://issues.liferay.com/browse/LPS-110283
[LPS-110422]: https://issues.liferay.com/browse/LPS-110422
[LPS-111291]: https://issues.liferay.com/browse/LPS-111291
[LPS-111461]: https://issues.liferay.com/browse/LPS-111461
[LPS-111896]: https://issues.liferay.com/browse/LPS-111896
[LPS-112500]: https://issues.liferay.com/browse/LPS-112500
[LPS-113024]: https://issues.liferay.com/browse/LPS-113024
[LPS-113624]: https://issues.liferay.com/browse/LPS-113624
[LPS-114570]: https://issues.liferay.com/browse/LPS-114570
[LPS-114882]: https://issues.liferay.com/browse/LPS-114882
[LPS-115020]: https://issues.liferay.com/browse/LPS-115020
[LPS-121824]: https://issues.liferay.com/browse/LPS-121824
[LPS-123192]: https://issues.liferay.com/browse/LPS-123192
[LPS-129874]: https://issues.liferay.com/browse/LPS-129874
[LPS-130505]: https://issues.liferay.com/browse/LPS-130505
[LPS-141250]: https://issues.liferay.com/browse/LPS-141250
[LPS-142100]: https://issues.liferay.com/browse/LPS-142100
[LPS-143280]: https://issues.liferay.com/browse/LPS-143280
[LRCI-2670]: https://issues.liferay.com/browse/LRCI-2670