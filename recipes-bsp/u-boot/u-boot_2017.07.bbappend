FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

DEPENDS += "virtual/arm-trusted-firmware"

UBOOT_SUFFIX = "itb"
SPL_BINARY = "spl/sunxi-spl.bin"

BOOT_FIRMWARE_BINARYNAME ?= "boot-firmware.bin"
BOOT_FIRMWARE_IMAGE ?= "${BOOT_FIRMWARE_BINARYNAME}-${MACHINE}-${PV}-${PR}"
BOOT_FIRMWARE_SYMLINK ?= "${BOOT_FIRMWARE_BINARYNAME}-${MACHINE}"

SRC_URI += "file://09-de2-setup-simplefb.patch"

do_compile_prepend() {
	export BL31="${DEPLOY_DIR_IMAGE}/bl31-${MACHINE}.bin"
}
do_compile[depends] = "virtual/arm-trusted-firmware:do_deploy"

do_deploy_append() {
    if [ -n "${UBOOT_CONFIG}" ]
    then
        for config in ${UBOOT_MACHINE}; do
            i=$(expr $i + 1);
            for type in ${UBOOT_CONFIG}; do
                j=$(expr $j + 1);
                if [ $j -eq $i ]
                then
                    cat ${B}/${config}/${SPL_BINARY} \
                        ${B}/${config}/u-boot-${type}.${UBOOT_SUFFIX} \
                        > ${DEPLOYDIR}/${BOOT_FIRMWARE_IMAGE}-${type}-${PV}-${PR}
                    rm -f ${DEPLOYDIR}/${BOOT_FIRMWARE_BINARYNAME} ${DEPLOYDIR}/${BOOT_FIRMWARE_SYMLINK}-${type}
                    ln -sf ${BOOT_FIRMWARE_IMAGE}-${type}-${PV}-${PR} ${DEPLOYDIR}/${BOOT_FIRMWARE_BINARYNAME}-${type}
                    ln -sf ${BOOT_FIRMWARE_IMAGE}-${type}-${PV}-${PR} ${DEPLOYDIR}/${BOOT_FIRMWARE_BINARYNAME}
                    ln -sf ${BOOT_FIRMWARE_IMAGE}-${type}-${PV}-${PR} ${DEPLOYDIR}/${BOOT_FIRMWARE_SYMLINK}-${type}
                    ln -sf ${BOOT_FIRMWARE_IMAGE}-${type}-${PV}-${PR} ${DEPLOYDIR}/${BOOT_FIRMWARE_SYMLINK}
                fi
            done
            unset  j
        done
        unset  i
    else
        cat ${B}/${SPL_BINARY} \
            ${B}/${UBOOT_BINARY} \
            > ${DEPLOYDIR}/${BOOT_FIRMWARE_IMAGE}
        rm -f ${DEPLOYDIR}/${BOOT_FIRMWARE_BINARYNAME} ${DEPLOYDIR}/${BOOT_FIRMWARE_SYMLINK}
        ln -sf ${BOOT_FIRMWARE_IMAGE} ${DEPLOYDIR}/${BOOT_FIRMWARE_BINARYNAME}
        ln -sf ${BOOT_FIRMWARE_IMAGE} ${DEPLOYDIR}/${BOOT_FIRMWARE_SYMLINK}
    fi
}

COMPATIBLE_MACHINE = "^(pine64)$"
