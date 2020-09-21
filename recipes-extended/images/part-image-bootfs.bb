SUMMARY = "bootfs Image"
LICENSE = "MIT"

inherit core-image

IMAGE_FSTYPES_remove = "wic"

IMAGE_PARTITION_MOUNTPOINT = "/boot"

EXTRA_IMAGECMD_ext4 = "-i 4096 -L bootfs -O ^metadata_csum,^dir_index"

# Reset image feature
IMAGE_FEATURE = ""

# Set ROOTFS_MAXSIZE to expected ROOTFS_SIZE to use the whole disk partition and leave extra space to user
IMAGE_ROOTFS_SIZE        = "65536"
IMAGE_ROOTFS_MAXSIZE     = "65536"
IMAGE_OVERHEAD_FACTOR    = "1"
IMAGE_ROOTFS_EXTRA_SPACE = "0"

# Reset PACKAGE_INSTALL to avoid getting installed packages added in machine through IMAGE_INSTALL_append:
PACKAGE_INSTALL = ""

# Reset LINGUAS_INSTALL to avoid getting installed any locale-base package
LINGUAS_INSTALL = ""
IMAGE_LINGUAS = ""

IMAGETYPE = "uimage"

# Add specific package for our image:
PACKAGE_INSTALL += " \
    kernel-devicetree \
    kernel-image-${IMAGETYPE}  \
    u-boot-exynos-extlinux \
"

# Reset LDCONFIG to avoid runing ldconfig on image.
LDCONFIGDEPEND = ""

# Remove from IMAGE_PREPROCESS_COMMAND useless buildinfo
IMAGE_PREPROCESS_COMMAND_remove = "buildinfo;"
# Remove from IMAGE_PREPROCESS_COMMAND the prelink_image as it could be run after
# we clean rootfs folder leading to cp error if '/etc/' folder is missing:
#   cp: cannot create regular file
#   No such file or directory
IMAGE_PREPROCESS_COMMAND_remove = "prelink_image;"

IMAGE_PREPROCESS_COMMAND_append = "reformat_rootfs;"

# Cleanup rootfs newly created
reformat_rootfs() {
    if [ -d ${IMAGE_ROOTFS}${IMAGE_PARTITION_MOUNTPOINT} ]; then
        # Keep only IMAGE_PARTITION_MOUNTPOINT folder
        for f in $(ls -d ${IMAGE_ROOTFS}/*/ | grep -v ${IMAGE_PARTITION_MOUNTPOINT}/)
        do
            rm -rf $f
        done

        # Move all expected files in /rootfs
        mv ${IMAGE_ROOTFS}${IMAGE_PARTITION_MOUNTPOINT}/* ${IMAGE_ROOTFS}/
        # Remove empty boot folder
        rm -rf ${IMAGE_ROOTFS}${IMAGE_PARTITION_MOUNTPOINT}/
    else
        bbwarn "${IMAGE_PARTITION_MOUNTPOINT} folder not available in rootfs folder, no reformat done..."
    fi
}
