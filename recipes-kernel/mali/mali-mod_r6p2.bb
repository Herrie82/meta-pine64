SUMMARY = "Mali-400 kernel module for Allwinner platforms"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://common/mali_mmu.c;beginline=1;endline=9;md5=aa5d0e27872273a9b813e9fe84f97fc6"

inherit module

SRC_URI = "git://github.com/mripard/sunxi-mali.git;protocol=git;branch=master"
SRC_URI += "file://0001-Makefile-modules-install-target.patch"
SRCREV = "3f30ecf91911d24fcc0b8dc764cc88832772c553"

S = "${WORKDIR}/git/r6p2/src/devicedrv/mali"

# USING_DEVFREQ=1...
EXTRA_OEMAKE += "USING_UMP=0 BUILD=release USING_PROFILING=0 MALI_PLATFORM=sunxi USING_DVFS=1 USING_DEVFREQ=0 KDIR=${STAGING_KERNEL_BUILDDIR}"

do_in_tree_patches () {
	cd "${S}/../../.."
	for patch in ../patches/*.patch; do
		patch -sf -p1 < $patch
		if [ $? -ne 0 ]; then
			bbfatal "Error applying patch $patch"
		fi
	done
}

addtask do_in_tree_patches after do_unpack before do_patch

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

COMPATIBLE_MACHINE = ""
COMPATIBLE_MACHINE_pine64 = "pine64"
