inherit kernel

do_configure:prepend() {
    oe_runmake -C ${S} O=${B} ${KBUILD_DEFCONFIG}
}
