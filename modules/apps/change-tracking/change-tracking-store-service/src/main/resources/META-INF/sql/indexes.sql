create index IX_D2EC53C2 on CTSContent (companyId, repositoryId, ctCollectionId);
create index IX_7639D584 on CTSContent (companyId, repositoryId, path_[$COLUMN_LENGTH:75$], ctCollectionId);
create index IX_A89623 on CTSContent (companyId, repositoryId, path_[$COLUMN_LENGTH:75$], storeType[$COLUMN_LENGTH:75$], ctCollectionId);
create unique index IX_AE0A5E27 on CTSContent (companyId, repositoryId, path_[$COLUMN_LENGTH:75$], version[$COLUMN_LENGTH:75$], storeType[$COLUMN_LENGTH:75$], ctCollectionId);
create index IX_6D216525 on CTSContent (companyId, repositoryId, storeType[$COLUMN_LENGTH:75$], ctCollectionId);
create index IX_C97BB67B on CTSContent (ctCollectionId);
create index IX_A205D291 on CTSContent (storeType[$COLUMN_LENGTH:75$], companyId, repositoryId, ctCollectionId);
create index IX_FC29DFD5 on CTSContent (storeType[$COLUMN_LENGTH:75$], companyId, repositoryId, path_[$COLUMN_LENGTH:75$], ctCollectionId);