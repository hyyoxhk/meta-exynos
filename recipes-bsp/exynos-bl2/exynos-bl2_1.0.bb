SUMMARY = "bl2 for exynos 4412"
HOMEPAGE = "https://github.com/hyyoxhk/exynos-bl2"
SECTION = "bootloader"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5e9d6f607b1367825bc48a88560a45af"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "bison-native"

SRC_URI = "git://github.com/hyyoxhk/exynos-bl2;protocol=https;branch=master"
SRCREV = "08323f7e56687b6b69ce4cb8be92c0cc17c78fac"

S = "${WORKDIR}/git"
B = "${WORKDIR}/git"

EXTRA_OEMAKE = "HOSTCC="${BUILD_CC}" \ 
                HOSTCXX="${BUILD_CXX}" \
                ARCH=${TARGET_ARCH} \
                CROSS_COMPILE=${TARGET_PREFIX}"

inherit deploy

do_configure() {
    cd ${S}
    oe_runmake itop_secure_defconfig
}

do_compile() {
    unset LDFLAGS
    cd ${S}
    oe_runmake
}

do_deploy() {
    install -d ${DEPLOYDIR}
    install -m 644 ${B}/exynos-bl2.img ${DEPLOYDIR}
    install -m 644 ${B}/firmware/E4412_N.bl1.SCP2G.bin ${DEPLOYDIR}
    install -m 644 ${B}/firmware/tzsw_SMDK4412_SCP_2GB.bin ${DEPLOYDIR}
}

addtask deploy before do_build after do_compile
