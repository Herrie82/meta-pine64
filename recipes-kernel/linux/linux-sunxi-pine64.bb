inherit kernel
require recipes-kernel/linux/linux-yocto.inc

BRANCH = "sunxi-next"
SRC_URI = "git://github.com/linux-sunxi/linux-sunxi.git;protocol=git;nocheckout=1;name=machine;branch=${BRANCH}"
SRC_URI += "file://0001-Add-a-simplefb-node-pine64.patch"
SRC_URI += "file://defconfig"

LINUX_VERSION ?= "4.12"
LINUX_VERSION_EXTENSION_append = "-${BRANCH}-4ca6df134847"

KERNEL_DEVICETREE = "allwinner/sun50i-a64-pine64.dtb allwinner/sun50i-a64-pine64-plus.dtb"
KCONFIG_MODE = "--alldefconfig"

SRCREV_machine = "4ca6df134847a6349620b485a3e63f00fb3bfad8"

PV = "${LINUX_VERSION}+git${SRCPV}"

COMPATIBLE_MACHINE = "(^$)"
COMPATIBLE_MACHINE_pine64 = "pine64"
