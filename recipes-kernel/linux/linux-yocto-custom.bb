inherit kernel
require recipes-kernel/linux/linux-yocto.inc

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git;protocol=git;nocheckout=1;name=machine"
SRC_URI += "file://0001-Add-a-simplefb-node-pine64.patch"
SRC_URI += "file://defconfig"

# Points to 4.12
SRCREV_machine = "6f7da290413ba713f0cdd9ff1a2a9bb129ef4f6c"

LINUX_VERSION ?= "4.12"
# LINUX_VERSION_EXTENSION_append = "-6f7da290413b"

KERNEL_DEVICETREE = "allwinner/sun50i-a64-pine64.dtb allwinner/sun50i-a64-pine64-plus.dtb"
KCONFIG_MODE = "--alldefconfig"

PV = "${LINUX_VERSION}+git${SRCPV}"

COMPATIBLE_MACHINE = "(^$)"
COMPATIBLE_MACHINE_pine64 = "pine64"
