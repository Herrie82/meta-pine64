DESCRIPTION = "Basic U-Boot script for pine64 mainline boot"
LICENSE = "CLOSED"
DEPENDS = "u-boot-mkimage-native"

inherit deploy

SRC_URI = "file://boot.scr"

S = "${WORKDIR}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install[noexec] = "1"

do_deploy() {
    mkimage -C none -A arm -T script -d ${S}/boot.scr ${DEPLOYDIR}/${MACHINE}-boot.scr.uimg
}
do_deploy[depends] = "u-boot-mkimage-native:do_populate_sysroot"

addtask deploy before do_build after do_compile

COMPATIBLE_MACHINE = "pine64"
PACKAGE_ARCH = "${MACHINE_ARCH}"
