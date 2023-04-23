SUMMARY = "Universal Boot Loader for embedded devices for exynos"
SECTION = "bootloaders"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=5a7450c57ffe5ae63fd732446b988025"

include u-boot-exynos.inc

SRC_URI = "https://ftp.denx.de/pub/u-boot/u-boot-${PV}.tar.bz2"
SRC_URI[md5sum] = "723310c588052a1331f2cc3f1e292504"
SRC_URI[sha256sum] = "9e05f36f2e6dc8ed30a2329e10f6296eb28b7e9e34e7b2f6e6a5d19fa9509d74"

SRC_URI += " \
    file://0001-Add-support-for-Davicom-DM96xx-based-USB-10-100-ethe.patch \
    file://0002-dts-add-device-tree-for-iTop-4412-board.patch \
    file://0003-ARM-Samsung-Add-support-for-iTop-4412-based-on-Samsu.patch \
"

S = "${WORKDIR}/u-boot-${PV}"
B = "${WORKDIR}/build"

BBCLASSEXTEND = "devupstream:target"
SRC_URI:class-devupstream = "git://github.com/hyyoxhk/u-boot.git;protocol=https;branch=v2020.10-rc3-itop"
SRCREV:class-devupstream = "${AUTOREV}"

SOURCE_SELECTION ?= "tarball"

DEFAULT_PREFERENCE = "${@bb.utils.contains('SOURCE_SELECTION', 'github', '-1', '1', d)}"

BBCLASSEXTEND = "devupstream:target"
