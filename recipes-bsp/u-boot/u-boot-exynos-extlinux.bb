SUMMARY = "Provide 'extlinux.conf' file for U-Boot"LICENSE = "MIT"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS += "u-boot-mkimage-native"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "file://extlinux.conf"

PV = "1.0"

inherit kernel-arch

B = "${WORKDIR}/build"

UBOOT_EXTLINUX_CONF = "extlinux.conf"

UBOOT_EXTLINUX_INSTALL_DIR ?= "/boot"

do_install() {
    install -d ${D}/${UBOOT_EXTLINUX_INSTALL_DIR}

    # Install extlinux files
    install -d ${D}/${UBOOT_EXTLINUX_INSTALL_DIR}/extlinux/
    cp ${WORKDIR}/${UBOOT_EXTLINUX_CONF} ${D}${UBOOT_EXTLINUX_INSTALL_DIR}/extlinux/
}
# TODO: FILES
FILES_${PN} = "${UBOOT_EXTLINUX_INSTALL_DIR}"
