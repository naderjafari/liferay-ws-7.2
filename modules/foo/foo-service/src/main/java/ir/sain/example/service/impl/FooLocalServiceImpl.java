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

package ir.sain.example.service.impl;

import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import ir.sain.example.model.Foo;
import ir.sain.example.service.base.FooLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.Date;

/**
 * The implementation of the foo local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>ir.sain.example.service.FooLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FooLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=ir.sain.example.model.Foo",
	service = AopService.class
)
public class FooLocalServiceImpl extends FooLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>ir.sain.example.service.FooLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>ir.sain.example.service.FooLocalServiceUtil</code>.
	 */

    @Override
    public Foo addFoo(
            long userId, String field1, boolean field2,int field3, Date field4, String field5, ServiceContext serviceContext)
            throws PortalException {

        // Foo

        User user = userLocalService.getUser(userId);
        long groupId = serviceContext.getScopeGroupId();

        long fooId = counterLocalService.increment();

        Foo foo = fooPersistence.create(fooId);

        foo.setUuid(serviceContext.getUuid());
        foo.setGroupId(groupId);
        foo.setCompanyId(user.getCompanyId());
        foo.setUserId(user.getUserId());
        foo.setUserName(user.getFullName());
        foo.setField1(field1);
        foo.setField2(field2);
        foo.setField3(field3);
        foo.setField4(field4);
        foo.setField5(field5);

        foo = fooPersistence.update(foo);

        // TODO: Resources

        // TODO: Asset

        // TODO: Workflow

        return foo;
    }

}