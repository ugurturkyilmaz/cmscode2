/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.security.sso.ntlm.internal.msrpc;

import jcifs.dcerpc.DcerpcMessage;
import jcifs.dcerpc.ndr.NdrBuffer;

/**
 * @author Marcellus Tavares
 */
public class NetrServerAuthenticate3 extends DcerpcMessage {

	public NetrServerAuthenticate3(
		String primaryName, String accountName, int secureChannelType,
		String computerName, byte[] clientCredential, byte[] serverCredential,
		int negotiateFlags) {

		_primaryName = primaryName;
		_accountName = accountName;
		_secureChannelType = (short)secureChannelType;
		_computerName = computerName;
		_clientCredential = clientCredential;
		_serverCredential = serverCredential;
		_negotiateFlags = negotiateFlags;

		ptype = 0;
		flags = DCERPC_FIRST_FRAG | DCERPC_LAST_FRAG;
	}

	@Override
	public void decode_out(NdrBuffer ndrBuffer) {
		int index = ndrBuffer.index;

		ndrBuffer.advance(8);

		ndrBuffer = ndrBuffer.derive(index);

		for (int i = 0; i < 8; i++) {
			_serverCredential[i] = (byte)ndrBuffer.dec_ndr_small();
		}

		_negotiateFlags = ndrBuffer.dec_ndr_long();
		_accountRid = ndrBuffer.dec_ndr_long();
		_status = ndrBuffer.dec_ndr_long();
	}

	@Override
	public void encode_in(NdrBuffer ndrBuffer) {
		ndrBuffer.enc_ndr_referent(_primaryName, 1);
		ndrBuffer.enc_ndr_string(_primaryName);
		ndrBuffer.enc_ndr_string(_accountName);
		ndrBuffer.enc_ndr_short(_secureChannelType);
		ndrBuffer.enc_ndr_string(_computerName);

		int index = ndrBuffer.index;

		ndrBuffer.advance(8);

		NdrBuffer derivedNdrBuffer = ndrBuffer.derive(index);

		for (int i = 0; i < 8; i++) {
			derivedNdrBuffer.enc_ndr_small(_clientCredential[i]);
		}

		ndrBuffer.enc_ndr_long(_negotiateFlags);
	}

	public int getAccountRid() {
		return _accountRid;
	}

	public int getNegotiatedFlags() {
		return _negotiateFlags;
	}

	@Override
	public int getOpnum() {
		return 26;
	}

	public byte[] getServerCredential() {
		return _serverCredential;
	}

	public int getStatus() {
		return _status;
	}

	private final String _accountName;
	private int _accountRid;
	private final byte[] _clientCredential;
	private final String _computerName;
	private int _negotiateFlags;
	private final String _primaryName;
	private final short _secureChannelType;
	private final byte[] _serverCredential;
	private int _status;

}