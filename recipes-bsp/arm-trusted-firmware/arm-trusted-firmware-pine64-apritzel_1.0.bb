DESCRIPTION = "ARM Trusted Firmware for Pine64 from Andre Przywara"
SECTION = "firmware"
PROVIDES = "virtual/arm-trusted-firmware"
LICENSE = "BSD"

inherit deploy

DEPENDS = ""
PARALLEL_MAKE=""

LIC_FILES_CHKSUM = 'file://license.md;md5=829bdeb34c1d9044f393d5a16c068371'

BRANCH = "allwinner"
SRC_URI = "git://github.com/apritzel/arm-trusted-firmware.git;protocol=git;branch=${BRANCH};destsuffix=${PN}-${PV}"
SRCREV = "87e8aedd80e6448a55b2328768d956fcb5f5d410"

COMPATIBLE_MACHINE = "pine64"
PLATFORM_pine64 = "sun50iw1p1"

# Let the Makefile handle setting up the CFLAGS and LDFLAGS as it is a standalone application
CFLAGS[unexport] = "1"
LDFLAGS[unexport] = "1"
AS[unexport] = "1"
LD[unexport] = "1"

do_configure[noexec] = "1"
do_install[noexec] = "1"

do_compile() {
	oe_runmake ARCH=arm CROSS_COMPILE="${TARGET_PREFIX}" PLAT="${PLATFORM}" bl31
}

do_deploy() {
	install -m 0644 ${S}/build/${PLATFORM}/release/bl31/bl31.elf ${DEPLOYDIR}/bl31-${MACHINE}.elf
	install -m 0644 ${S}/build/${PLATFORM}/release/bl31.bin ${DEPLOYDIR}/bl31-${MACHINE}.bin
}
addtask deploy before do_build after do_compile
