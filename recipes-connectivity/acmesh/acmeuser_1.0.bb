SUMMARY = "Create the "acme" user to use acme.sh script implementing ACME client protocol"
PR = "r1"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

RDEPENDS_${PN} = "\
    bash \
"

S = "${WORKDIR}"

EXCLUDE_FROM_WORLD = "1"

inherit useradd

# You must set USERADD_PACKAGES when you inherit useradd. This
# lists which output packages will include the user/group
# creation code.
USERADD_PACKAGES = "${PN}"

# You must also set USERADD_PARAM and/or GROUPADD_PARAM when
# you inherit useradd.

# USERADD_PARAM specifies command line options to pass to the
# useradd command. Here we'll create the "acme" user:
USERADD_PARAM_${PN} = "-u 5001 -g 5001 -d /home/acme -m -s /bin/bash acme"

# GROUPADD_PARAM works the same way, which you set to the options
# you'd normally pass to the groupadd command. This will create
# group acme:
GROUPADD_PARAM_${PN} = "-g 5001 acme"

do_install () {
	install -d -m 755 ${D}${datadir}/acme

	# The new users and groups are created before the do_install
	# step, so you are now free to make use of them:
	chown -R acme ${D}${datadir}/acme
	chgrp -R acme ${D}${datadir}/acme
}

FILES_${PN} = "${datadir}/acme"

# Prevents do_package failures with:
# debugsources.list: No such file or directory:
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
