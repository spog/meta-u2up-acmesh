SUMMARY = "A pure Unix shell script implementing ACME client protocol"
HOMEPAGE = "https://acme.sh/"
BUGTRACKER = "https://github.com/Neilpang/acme.sh/issues"
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PV = "2.8.4+git${SRCPV}"
SRCREV = "aac9f089d9c63cd753ac9dd9ab3b52e9cd6af3d8"
SRC_URI = "git://github.com/Neilpang/acme.sh.git;branch=master \
"
SRC_URI[md5sum] = "5ef954d9b6b244ffeabcd226be1867a0"
SRC_URI[sha256sum] = "039ad56ea6d6553aadf33243ea5b39802d73519e46a89c80c648b2bd1ec78aeb"

RDEPENDS_${PN} = "\
    sh \
"

