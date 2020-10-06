SUMMARY = "Linux exynox Kernel"
SECTION = "kernel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

include linux-exynos.inc

SRC_URI = "https://cdn.kernel.org/pub/linux/kernel/v5.x/linux-${PV}.tar.xz"
SRC_URI[md5sum] = "ffdb73ce9e792e141baa72628dd21230"
SRC_URI[sha256sum] = "93293032aa13c3998eeb2afd910f11c0f2e8a76ffec46f74ce3fcfac53ed60f1"

SRC_URI += "file://0001-Add-support-for-iTop-4412-based-on-Samsung-Exynos441.patch"

B = "${WORKDIR}/build"
S = "${WORKDIR}/linux-${PV}"

BBCLASSEXTEND = "devupstream:target"
SRC_URI_class-devupstream = "git://github.com/torvalds/linux.git"
SRCREV_class-devupstream = "${AUTOREV}"

SOURCE_SELECTION ?= "tarball"

DEFAULT_PREFERENCE = "${@bb.utils.contains('SOURCE_SELECTION', 'github', '-1', '1', d)}"

KERNEL_EXTRA_ARGS += "LOADADDR=${KERNEL_LOADADDR}"
