#!/bin/bash

U2UP_ACMESH="${HOME}/.acmesh"
U2UP_ACME="${HOME}/.acme"
U2UP_ACCOUNTEMAIL=$1

ln -s /usr/share/acme acme.sh
cd acme.sh
./acme.sh \
 --install \
 --home $U2UP_ACMESH \
 --config-home $U2UP_ACME/data \
 --accountconf $U2UP_ACME/account.conf \
 --accountkey $U2UP_ACME/account.key \
 --cert-home $U2UP_ACME/certs \
 --accountemail "${U2UP_ACCOUNTEMAIL}"

rv=$?

cd - > /dev/null
rm -f acme.sh

if [ $rv -eq 0 ]; then
	source .profile
	${U2UP_ACMESH}/acme.sh --config-home ${U2UP_ACME}/data --show-config
	rv=$?
fi

exit $rv
