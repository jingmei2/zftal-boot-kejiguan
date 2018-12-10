
delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N00';
delete from zftal_xtgl_gnmkczb where gnmkdm = 'N00';
delete from zftal_xtgl_jsgnmkczb where gnczid like 'N00%' and jsdm='admin';

-- Create table
create table ZFTAL_SIMPLE_SIMPLE (
  ID      	VARCHAR2(32) default sys_guid() not null,
  NAME    	VARCHAR2(100)
);
-- Add comments to the table
comment on table ZFTAL_SIMPLE_SIMPLE  is '例子表';
-- Add comments to the columns
comment on column ZFTAL_SIMPLE_SIMPLE.ID  is 'ID';
comment on column ZFTAL_SIMPLE_SIMPLE.NAME  is '名称';
-- Create/Recreate primary, unique and foreign key constraints
alter table ZFTAL_SIMPLE_SIMPLE add constraint PK_ZFTAL_SIMPLE_SIMPLE primary key (ID);

/**************  功能菜单（业务系统功能菜单请从N05之后开始）**************/
/**************添加一级菜单**************/
delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N06';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N06','一级','N','','1');

/**************添加二级菜单**************/
delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N0601';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N0601','二级','N06','','2');

/**************添加三级菜单**************/
delete from zftal_xtgl_gnmkdmb where gnmkdm = 'N060101';
insert into zftal_xtgl_gnmkdmb(gnmkdm,gnmkmc,fjgndm,dyym,xssx) values ('N060101','三级','N0601','/test','3');

delete from zftal_xtgl_gnmkczb where gnmkdm = 'N060101';
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N060101_#','N060101','#','test:cx','');
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N060101_zj','N060101','zj','test:zj','1');
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N060101_xg','N060101','xg','test:xg','2');
insert into zftal_xtgl_gnmkczb(gnczid,gnmkdm,czdm,qxdm,xssx) values ('N060101_sc','N060101','sc','test:sc','3');

delete from zftal_xtgl_jsgnmkczb where gnczid like 'N060101%' and jsdm='admin';
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N060101_#');
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N060101_zj');
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N060101_xg');
insert into zftal_xtgl_jsgnmkczb(jsdm,gnczid) values ('admin','N060101_sc');

/**************删除框架默认菜单，添加自定义菜单**************/
delete from zftal_xtgl_gnmkdmb;
insert into zftal_xtgl_gnmkdmb (gnmkdm, gnmkmc, gnmkywmc, gnmkjc, gnmkywjc, fjgndm, dyym, xssx, gnsydx, sfxs, sfzdymk, tblj)
values ('N08', '基础分析', null, null, null, 'N', '#', '1', null, '1', '0', null);

insert into zftal_xtgl_gnmkdmb (gnmkdm, gnmkmc, gnmkywmc, gnmkjc, gnmkywjc, fjgndm, dyym, xssx, gnsydx, sfxs, sfzdymk, tblj)
values ('N0803', '用户分析', null, null, null, 'N08', '#', '1', null, '1', '0', 'fa fa-user');

values ('N080301', '活跃度', null, null, null, 'N0803', '/yhfx/ssyhgj', '1', null, '1', '0', 'fa fa-group');
insert into zftal_xtgl_gnmkdmb (gnmkdm, gnmkmc, gnmkywmc, gnmkjc, gnmkywjc, fjgndm, dyym, xssx, gnsydx, sfxs, sfzdymk, tblj)
values ('N080302', '用户行为', null, null, null, 'N0803', '/yhfx/xlyh', '2', null, '1', '0', 'fa fa-group');
insert into zftal_xtgl_gnmkdmb (gnmkdm, gnmkmc, gnmkywmc, gnmkjc, gnmkywjc, fjgndm, dyym, xssx, gnsydx, sfxs, sfzdymk, tblj)
values ('N080303', '用户画像', null, null, null, 'N0803', '/yhfx/yhhx', '3', null, '1', '0', 'fa fa-group');
insert into zftal_xtgl_gnmkdmb (gnmkdm, gnmkmc, gnmkywmc, gnmkjc, gnmkywjc, fjgndm, dyym, xssx, gnsydx, sfxs, sfzdymk, tblj)
values ('N080401', '区域', null, null, null, 'N0804', '/khdfx/qy', '1', null, '1', '0', 'fa fa-group');

