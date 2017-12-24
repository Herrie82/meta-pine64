inherit kernel
require recipes-kernel/linux/linux-yocto.inc

BRANCH = "testing/sunxi64-4.15-rc5-drm"
MY_SRCREV = "75ca7c30ea76c00a0fccca2710ffe26e2bbb52c6"
MY_SRCREV_DEFCONFIG = "464e1d5f23cca236b930ef068c328a64cab78fb1"
SRC_URI = "git://github.com/r1mikey/linux-r1mikey.git;protocol=git;nocheckout=1;name=machine;branch=${BRANCH}"
# SRC_URI = "git:///home/michael/development/linux-sunxi64-rmikey;protocol=file;nocheckout=1;name=machine;branch=${BRANCH}"
SRC_URI += "https://git.kernel.org/pub/scm/linux/kernel/git/torvalds/linux.git/plain/arch/arm64/configs/defconfig?id=${MY_SRCREV_DEFCONFIG};name=defconfig;downloadfilename=defconfig"
SRC_URI += "file://new-configs.cfg"
SRCREV_machine = "${MY_SRCREV}"
LINUX_VERSION ?= "4.15-rc5+"

SRC_URI[defconfig.md5sum] = "c4dcb6a2048bcb72878fd8e8a4068bf0"
SRC_URI[defconfig.sha256sum] = "79d5a85bc1797edb6010888a35f09ab0cc794329f38ce498e21b1180b4027eee"

KERNEL_DEVICETREE = "allwinner/sun50i-a64-pine64.dtb allwinner/sun50i-a64-pine64-plus.dtb"
KCONFIG_MODE = "--alldefconfig"

PV = "${LINUX_VERSION}+git${SRCPV}"

COMPATIBLE_MACHINE = "(^$)"
COMPATIBLE_MACHINE_pine64 = "pine64"
