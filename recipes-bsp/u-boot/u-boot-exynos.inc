require recipes-bsp/u-boot/u-boot.inc

DEPENDS += "dtc-native bc-native"
DEPENDS += "flex-native bison-native"

STAGING_UBOOT_DIR = "${TMPDIR}/work-shared/${MACHINE}/uboot-source"

# this is copying form kernel.bbclass
# Old style kernels may set ${S} = ${WORKDIR}/git for example
# We need to move these over to STAGING_KERNEL_DIR. We can't just
# create the symlink in advance as the git fetcher can't cope with
# the symlink.
do_unpack[cleandirs] += " ${S} ${STAGING_UBOOT_DIR}"
do_clean[cleandirs] += " ${S} ${STAGING_UBOOT_DIR}"

# this is copying form kernel.bbclass
base_do_unpack:append () {
    # Copy/Paste from kernel class with adaptation to UBOOT var
    s = d.getVar("S")
    if s[-1] == '/':
        # drop trailing slash, so that os.symlink(ubootsrc, s) doesn't use s as directory name and fail
        s=s[:-1]
    ubootsrc = d.getVar("STAGING_UBOOT_DIR")
    if s != ubootsrc:
        bb.utils.mkdirhier(ubootsrc)
        bb.utils.remove(ubootsrc, recurse=True)
        if d.getVar("EXTERNALSRC"):
            # With EXTERNALSRC S will not be wiped so we can symlink to it
            os.symlink(s, ubootsrc)
        else:
            import shutil
            shutil.move(s, ubootsrc)
            os.symlink(ubootsrc, s)
}

do_compile:append() {
    oe_runmake -C ${S} O=${B} DEVICE_TREE=${UBOOT_DEVICETREE}
}

UBOOT_INITIAL_ENV = "u-boot-initial-env"

#INSANE_SKIP:${PN} += "ldflags"

#INSANE_SKIP:${PN} += "textrel"
