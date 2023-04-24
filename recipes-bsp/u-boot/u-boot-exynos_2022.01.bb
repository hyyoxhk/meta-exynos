SUMMARY = "Universal Boot Loader for embedded devices for exynos"
SECTION = "bootloaders"
LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=5a7450c57ffe5ae63fd732446b988025"

include u-boot-exynos.inc

SRC_URI = "https://ftp.denx.de/pub/u-boot/u-boot-${PV}.tar.bz2"
SRC_URI[md5sum] = "49ba74a214d14c6340c8067bc1ed5fa0"
SRC_URI[sha256sum] = "81b4543227db228c03f8a1bf5ddbc813b0bb8f6555ce46064ef721a6fc680413"

SRC_URI += " \
    file://0001-Add-support-for-Davicom-DM96xx-based-USB-10-100-ethe.patch \
    file://0002-env-fat-Add-new-lines-at-the-end-of-print-statements.patch \
    file://0003-env-fat-Allow-overriding-interface-device-and-partit.patch \
    file://0004-dts-add-device-tree-for-iTop-4412-board.patch \
    file://0005-ARM-Samsung-Add-support-for-iTop-4412-based-on-Samsu.patch \
"

S = "${WORKDIR}/u-boot-${PV}"
B = "${WORKDIR}/build"

BBCLASSEXTEND = "devupstream:target"
SRC_URI:class-devupstream = "git://github.com/hyyoxhk/u-boot.git;protocol=https;branch=v2022.01-itop"
SRCREV:class-devupstream = "6448740a933765d281ffd66105ef85abd1dbc9ac"

SOURCE_SELECTION ?= "tarball"

DEFAULT_PREFERENCE = "${@bb.utils.contains('SOURCE_SELECTION', 'github', '-1', '1', d)}"

BBCLASSEXTEND = "devupstream:target"
