SUMMARY = "Linux exynox Kernel"
SECTION = "kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

include linux-exynos.inc

SRC_URI = "https://cdn.kernel.org/pub/linux/kernel/v5.x/linux-${PV}.tar.xz"
SRC_URI[md5sum] = "79f1309666d782d20f6e183e91468200"
SRC_URI[sha256sum] = "8beb69ada46f1cbca2f4cf901ec078846035c1cd925d9471422f65aff74243ba"

SRC_URI += " \
    file://0001-arm-5.15.108-devicetree.patch \
    file://0002-arm-5.15.108-config.patch \
"

B = "${WORKDIR}/build"
S = "${WORKDIR}/linux-${PV}"

BBCLASSEXTEND = "devupstream:target"
SRC_URI:class-devupstream = "git://github.com/torvalds/linux.git;protocol=https;branch=master"
SRCREV:class-devupstream = "${AUTOREV}"

SOURCE_SELECTION ?= "tarball"

DEFAULT_PREFERENCE = "${@bb.utils.contains('SOURCE_SELECTION', 'github', '-1', '1', d)}"

KERNEL_EXTRA_ARGS += "LOADADDR=${KERNEL_LOADADDR}"

# Do not deploy kernel module with specfic tarball
MODULE_TARBALL_DEPLOY = "0"
