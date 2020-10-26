UBOOT_EXTLINUX = "1"

# Define extlinux console
UBOOT_EXTLINUX_CONSOLE = "console=${@d.getVar('SERIAL_CONSOLE').split()[1]},${@d.getVar('SERIAL_CONSOLE').split()[0]}"
# Define extlinux labels
UBOOT_EXTLINUX_LABELS = "exynos4412-itop-elite-sdcard"
# Define extlinux timeout
UBOOT_EXTLINUX_TIMEOUT = "20"

UBOOT_EXTLINUX_ROOT = "root=/dev/mmcblk0p3"
UBOOT_EXTLINUX_KERNEL_IMAGE_exynos4412-itop-elite-sdcard = "/uImage"
UBOOT_EXTLINUX_FDT_exynos4412-itop-elite-sdcard = "/exynos4412-itop-elite.dtb"
