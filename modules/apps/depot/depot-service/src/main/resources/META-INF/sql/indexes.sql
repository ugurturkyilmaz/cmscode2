create index IX_5B76D798 on DepotAppCustomization (depotEntryId, enabled);
create unique index IX_DA8D9ACC on DepotAppCustomization (depotEntryId, portletId[$COLUMN_LENGTH:75$]);

create unique index IX_884D6226 on DepotEntry (groupId);
create index IX_FBDFFFF8 on DepotEntry (uuid_[$COLUMN_LENGTH:75$], companyId);

create index IX_F329B161 on DepotEntryGroupRel (ddmStructuresAvailable, toGroupId);
create unique index IX_65D34444 on DepotEntryGroupRel (depotEntryId, toGroupId);
create index IX_C61C803B on DepotEntryGroupRel (searchable, toGroupId);
create index IX_DB75E9F1 on DepotEntryGroupRel (toGroupId);
create index IX_7ED7EAB2 on DepotEntryGroupRel (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_D25F75B4 on DepotEntryGroupRel (uuid_[$COLUMN_LENGTH:75$], groupId);