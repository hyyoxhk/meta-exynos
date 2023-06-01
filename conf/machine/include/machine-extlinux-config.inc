UBOOT_EXTLINUX = "1"

# Define extlinux console
UBOOT_EXTLINUX_CONSOLE = "console=${@d.getVar('SERIAL_CONSOLE').split()[1]},${@d.getVar('SERIAL_CONSOLE').split()[0]}"
# Define extlinux labels
UBOOT_EXTLINUX_LABELS = "exynos4412-itop-elite-sdcard"
# Define extlinux timeout
UBOOT_EXTLINUX_TIMEOUT = "20"
# Define additional kernel arguments
UBOOT_EXTLINUX_KERNEL_ARGS = "quiet"

UBOOT_EXTLINUX_ROOT = "LABEL=${LABEL} root=${mmcroot}"
UBOOT_EXTLINUX_KERNEL_IMAGE:exynos4412-itop-elite-sdcard = "/uImage"
UBOOT_EXTLINUX_FDT:exynos4412-itop-elite-sdcard = "/exynos4412-itop-elite.dtb"
UBOOT_EXTLINUX_INITRD = "/initrd.img"
