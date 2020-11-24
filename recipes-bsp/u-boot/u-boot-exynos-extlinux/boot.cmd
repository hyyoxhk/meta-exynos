# Generate boot.scr:
# ./tools/mkimage -C none -A arm -T script -d boot.cmd boot.scr
#

echo "Executing SCRIPT on target=${target}"

# Update the DISTRO command to search in sub-directory
env set boot_prefixes "/${boot_device}${boot_instance}_"

# save the boot config for the 2nd boot
env set boot_targets ${target}

if test ${target} = mmc0; then
    if test -d ${devtype} ${devnum}:${distro_bootpart} /mmc0_extlinux; then
        env set boot_prefixes "/mmc0_"
    fi
elif test ${target} = mmc1; then
    if test -d ${devtype} ${devnum}:${distro_bootpart} /mmc1_extlinux; then
        env set boot_prefixes "/mmc1_"
    fi
fi

if test -e ${devtype} ${devnum}:${distro_bootpart} ${boot_prefixes}extlinux/${board_name}_extlinux.conf; then
    echo FOUND ${boot_prefixes}extlinux/${board_name}_extlinux.conf
    env set boot_syslinux_conf "extlinux/${board_name}_extlinux.conf"
fi

# don't save the updated content of bootfile variable to avoid conflict
env delete bootfile

# save the boot config the 2nd boot (boot_prefixes/boot_extlinux)
env save

# start the correct exlinux.conf
run bootcmd_${target}

echo SCRIPT FAILED... ${boot_prefixes}${boot_syslinux_conf} not found !

# restore environment to default value when failed
env default boot_targets
env default boot_prefixes
env default boot_extlinux
env default boot_syslinux_conf
env save