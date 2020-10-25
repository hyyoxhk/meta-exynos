inherit kernel

# Do not deploy kernel module with specfic tarball
MODULE_TARBALL_DEPLOY = "0"

KERNEL_EXTRA_ARGS += "LOADADDR=${KERNEL_LOADADDR}"

do_configure_prepend() {
    oe_runmake -C ${S} O=${B} ${KBUILD_DEFCONFIG}
}

BBCLASSEXTEND = "devupstream:target"
SRC_URI_class-devupstream = "git://github.com/torvalds/linux.git"
SRCREV_class-devupstream = "${AUTOREV}"

SOURCE_SELECTION ?= "tarball"

DEFAULT_PREFERENCE = "${@bb.utils.contains('SOURCE_SELECTION', 'github', '-1', '1', d)}"

MODULE_TARBALL_DEPLOY = "0"
