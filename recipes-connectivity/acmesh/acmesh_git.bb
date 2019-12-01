SUMMARY = "A pure Unix shell script implementing ACME client protocol"
HOMEPAGE = "https://acme.sh/"
BUGTRACKER = "https://github.com/Neilpang/acme.sh/issues"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://git/LICENSE.md;md5=1ebbd3e34237af26da5dc08a4e440464"

PV = "2.8.4+git${SRCPV}"
SRCREV = "aac9f089d9c63cd753ac9dd9ab3b52e9cd6af3d8"
SRC_URI = "git://github.com/Neilpang/acme.sh.git;branch=master \
"
SRC_URI[md5sum] = "5ef954d9b6b244ffeabcd226be1867a0"
SRC_URI[sha256sum] = "039ad56ea6d6553aadf33243ea5b39802d73519e46a89c80c648b2bd1ec78aeb"

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
	install -d -m 755 ${D}${datadir}
	cp -a git ${D}${datadir}/acme
	rm -rf ${D}${datadir}/acme/.git
	rm -rf ${D}${datadir}/acme/.github

	# The new users and groups are created before the do_install
	# step, so you are now free to make use of them:
	chown -R acme ${D}${datadir}/acme
	chgrp -R acme ${D}${datadir}/acme
}

FILES_${PN} = "${datadir}/acme"

# Prevents do_package failures with:
# debugsources.list: No such file or directory:
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

