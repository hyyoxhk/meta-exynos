SUMMARY = "Universal Boot Loader for embedded devices for exynos"
SECTION = "bootloaders"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=2ca5f2c35c8cc335f0a19756634782f1"

include u-boot-exynos.inc

SRC_URI = "https://ftp.denx.de/pub/u-boot/u-boot-${PV}.tar.bz2 \
           file://fw_env.config \
"
SRC_URI[md5sum] = "b90cb4a3b8f02f18872197b052222d19"
SRC_URI[sha256sum] = "e31cac91545ff41b71cec5d8c22afd695645cd6e2a442ccdacacd60534069341"

SRC_URI += " \
    file://0001-arm-v2023.04-drivers.patch \
    file://0002-arm-v2023.04-devicetree.patch \
    file://0003-arm-v2023.04-board.patch \
    file://0004-arm-v2023.04-config.patch \
    file://0005-Revert-mmc-s5p_sdhci-unset-the-SDHCI_QUIRK_BROKEN_R1.patch \
"

S = "${WORKDIR}/u-boot-${PV}"
B = "${WORKDIR}/build"

U_BOOT_VERSION = "${PV}"
U_BOOT_BRANCH = "v{U_BOOT_VERSION}-itop"

BBCLASSEXTEND = "devupstream:target"
SRC_URI:class-devupstream = "git://github.com/hyyoxhk/u-boot.git;protocol=https;branch=${U_BOOT_BRANCH}"
SRCREV:class-devupstream = "4cca10c0f6e26717b5f08d434fccca6fa4011012"

SOURCE_SELECTION ?= "tarball"

DEFAULT_PREFERENCE = "${@bb.utils.contains('SOURCE_SELECTION', 'github', '-1', '1', d)}"

BBCLASSEXTEND = "devupstream:target"
