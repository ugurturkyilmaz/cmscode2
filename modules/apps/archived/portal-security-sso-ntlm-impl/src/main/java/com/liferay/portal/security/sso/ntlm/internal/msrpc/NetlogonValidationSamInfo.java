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

import jcifs.dcerpc.ndr.NdrBuffer;
import jcifs.dcerpc.ndr.NdrException;
import jcifs.dcerpc.ndr.NdrObject;
import jcifs.dcerpc.rpc;

/**
 * @author Marcellus Tavares
 */
public class NetlogonValidationSamInfo extends NdrObject {

	public NetlogonValidationSamInfo() {
		_effectiveName = new rpc.unicode_string();
		_fullName = new rpc.unicode_string();
		_logonScript = new rpc.unicode_string();
		_profilePath = new rpc.unicode_string();
		_homeDirectory = new rpc.unicode_string();
		_homeDirectoryDrive = new rpc.unicode_string();
		_logonServer = new rpc.unicode_string();
		_logonDomainName = new rpc.unicode_string();
		_userSessionKey = new byte[16];
		_logonDomain = new rpc.sid_t();
	}

	@Override
	public void decode(NdrBuffer ndrBuffer) throws NdrException {
		_logonTime = ndrBuffer.dec_ndr_hyper();
		_logoffTime = ndrBuffer.dec_ndr_hyper();
		_kickoffTime = ndrBuffer.dec_ndr_hyper();
		_passwordLastSet = ndrBuffer.dec_ndr_hyper();
		_passwordCanChange = ndrBuffer.dec_ndr_hyper();
		_passwordMustChange = ndrBuffer.dec_ndr_hyper();

		_effectiveName.length = (short)ndrBuffer.dec_ndr_short();
		_effectiveName.maximum_length = (short)ndrBuffer.dec_ndr_short();

		int effectiveNamePtr = ndrBuffer.dec_ndr_long();

		_fullName.length = (short)ndrBuffer.dec_ndr_short();
		_fullName.maximum_length = (short)ndrBuffer.dec_ndr_short();

		int fullNamePtr = ndrBuffer.dec_ndr_long();

		_logonScript.length = (short)ndrBuffer.dec_ndr_short();
		_logonScript.maximum_length = (short)ndrBuffer.dec_ndr_short();

		int logonScriptPtr = ndrBuffer.dec_ndr_long();

		_profilePath.length = (short)ndrBuffer.dec_ndr_short();
		_profilePath.maximum_length = (short)ndrBuffer.dec_ndr_short();

		int profilePathPtr = ndrBuffer.dec_ndr_long();

		_homeDirectory.length = (short)ndrBuffer.dec_ndr_short();
		_homeDirectory.maximum_length = (short)ndrBuffer.dec_ndr_short();

		int homeDirectoryPtr = ndrBuffer.dec_ndr_long();

		_homeDirectoryDrive.length = (short)ndrBuffer.dec_ndr_short();
		_homeDirectoryDrive.maximum_length = (short)ndrBuffer.dec_ndr_short();

		int homeDirectoryDrivePtr = ndrBuffer.dec_ndr_long();

		_logonCount = (short)ndrBuffer.dec_ndr_short();
		_badPasswordCount = (short)ndrBuffer.dec_ndr_short();

		_userId = ndrBuffer.dec_ndr_long();
		_primaryGroupId = ndrBuffer.dec_ndr_long();

		_groupCount = ndrBuffer.dec_ndr_long();

		int groupIdsPtr = ndrBuffer.dec_ndr_long();

		_userFlags = ndrBuffer.dec_ndr_long();

		int userSessionKeyI = ndrBuffer.index;

		ndrBuffer.advance(16);

		_logonServer.length = (short)ndrBuffer.dec_ndr_short();
		_logonServer.maximum_length = (short)ndrBuffer.dec_ndr_short();

		int logonServerPtr = ndrBuffer.dec_ndr_long();

		_logonDomainName.length = (short)ndrBuffer.dec_ndr_short();
		_logonDomainName.maximum_length = (short)ndrBuffer.dec_ndr_short();

		int logonDomainNamePtr = ndrBuffer.dec_ndr_long();

		int logonDomainPtr = ndrBuffer.dec_ndr_long();

		ndrBuffer.advance(40);

		if (effectiveNamePtr > 0) {
			_decodeUnicodeString(ndrBuffer, _effectiveName);
		}

		if (fullNamePtr > 0) {
			_decodeUnicodeString(ndrBuffer, _fullName);
		}

		if (logonScriptPtr > 0) {
			_decodeUnicodeString(ndrBuffer, _logonScript);
		}

		if (profilePathPtr > 0) {
			_decodeUnicodeString(ndrBuffer, _profilePath);
		}

		if (homeDirectoryPtr > 0) {
			_decodeUnicodeString(ndrBuffer, _homeDirectory);
		}

		if (homeDirectoryDrivePtr > 0) {
			_decodeUnicodeString(ndrBuffer, _homeDirectoryDrive);
		}

		if (groupIdsPtr > 0) {
			_groupIds = new GroupMembership[_groupCount];

			ndrBuffer = ndrBuffer.deferred;

			int groupIdsS = ndrBuffer.dec_ndr_long();
			int groupIdsI = ndrBuffer.index;

			ndrBuffer.advance(8 * groupIdsS);

			ndrBuffer = ndrBuffer.derive(groupIdsI);

			for (int i = 0; i < groupIdsS; i++) {
				if (_groupIds[i] == null) {
					_groupIds[i] = new GroupMembership();
				}

				_groupIds[i].decode(ndrBuffer);
			}
		}

		ndrBuffer = ndrBuffer.derive(userSessionKeyI);

		for (int i = 0; i < 16; i++) {
			_userSessionKey[i] = (byte)ndrBuffer.dec_ndr_small();
		}

		if (logonServerPtr > 0) {
			_decodeUnicodeString(ndrBuffer, _logonServer);
		}

		if (logonDomainNamePtr > 0) {
			_decodeUnicodeString(ndrBuffer, _logonDomainName);
		}

		if (logonDomainPtr > 0) {
			ndrBuffer = ndrBuffer.deferred;

			_logonDomain.decode(ndrBuffer);
		}
	}

	@Override
	public void encode(NdrBuffer ndrBuffer) {
	}

	public rpc.unicode_string getEffectiveName() {
		return _effectiveName;
	}

	private void _decodeUnicodeString(
		NdrBuffer ndrBuffer, rpc.unicode_string string) {

		ndrBuffer = ndrBuffer.deferred;

		int bufferS = ndrBuffer.dec_ndr_long();

		ndrBuffer.dec_ndr_long();

		int bufferL = ndrBuffer.dec_ndr_long();
		int bufferI = ndrBuffer.index;

		ndrBuffer.advance(2 * bufferL);

		if (string.buffer == null) {
			string.buffer = new short[bufferS];
		}

		ndrBuffer = ndrBuffer.derive(bufferI);

		for (int i = 0; i < bufferL; i++) {
			string.buffer[i] = (short)ndrBuffer.dec_ndr_short();
		}
	}

	@SuppressWarnings("unused")
	private short _badPasswordCount;

	private final rpc.unicode_string _effectiveName;
	private final rpc.unicode_string _fullName;
	private int _groupCount;
	private GroupMembership[] _groupIds;
	private final rpc.unicode_string _homeDirectory;
	private final rpc.unicode_string _homeDirectoryDrive;

	@SuppressWarnings("unused")
	private long _kickoffTime;

	@SuppressWarnings("unused")
	private long _logoffTime;

	@SuppressWarnings("unused")
	private short _logonCount;

	private final rpc.sid_t _logonDomain;
	private final rpc.unicode_string _logonDomainName;
	private final rpc.unicode_string _logonScript;
	private final rpc.unicode_string _logonServer;

	@SuppressWarnings("unused")
	private long _logonTime;

	@SuppressWarnings("unused")
	private long _passwordCanChange;

	@SuppressWarnings("unused")
	private long _passwordLastSet;

	@SuppressWarnings("unused")
	private long _passwordMustChange;

	@SuppressWarnings("unused")
	private int _primaryGroupId;

	private final rpc.unicode_string _profilePath;

	@SuppressWarnings("unused")
	private int _userFlags;

	@SuppressWarnings("unused")
	private int _userId;

	private final byte[] _userSessionKey;

}