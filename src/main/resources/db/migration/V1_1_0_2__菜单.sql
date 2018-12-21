
-- Create table

-- Add comments to the table
-- Add comments to the columns
-- Create/Recreate primary, unique and foreign key constraints

/**************  功能菜单（业务系统功能菜单请从N05之后开始）**************/
/**************添加一级菜单**************/
/**************添加二级菜单**************/
/**************添加三级菜单**************/

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

