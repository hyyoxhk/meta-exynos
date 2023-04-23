SUMMARY = "Linux exynox Kernel"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

include linux-exynos.inc

SRC_URI = "https://cdn.kernel.org/pub/linux/kernel/v5.x/linux-${PV}.tar.xz"
SRC_URI[md5sum] = "83f75973e3d83a5f7b1c2e6c05abb9e4"
SRC_URI[sha256sum] = "f25936177edd183dd0645dce1d81873bcfc0bab1ff1586df75d95cd12723320d"

SRC_URI += "file://0001-Add-support-for-iTop-4412-based-on-Samsung-Exynos441.patch"

B = "${WORKDIR}/build"
S = "${WORKDIR}/linux-${PV}"

BBCLASSEXTEND = "devupstream:target"
SRC_URI:class-devupstream = "git://github.com/torvalds/linux.git"
SRCREV:class-devupstream = "${AUTOREV}"

SOURCE_SELECTION ?= "tarball"

DEFAULT_PREFERENCE = "${@bb.utils.contains('SOURCE_SELECTION', 'github', '-1', '1', d)}"

KERNEL_EXTRA_ARGS += "LOADADDR=${KERNEL_LOADADDR}"
