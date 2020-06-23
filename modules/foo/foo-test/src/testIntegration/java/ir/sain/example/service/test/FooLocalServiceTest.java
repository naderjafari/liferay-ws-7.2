package ir.sain.example.service.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.service.test.ServiceTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import ir.sain.example.model.Foo;
import ir.sain.example.service.FooLocalService;
import org.junit.*;
import org.junit.runner.RunWith;

/**
 * @author Nader Jafari
 */
@RunWith(Arquillian.class)
public class FooLocalServiceTest {

    @ClassRule
    @Rule
    public static final AggregateTestRule aggregateTestRule =
            new LiferayIntegrationTestRule();

    @Before
    public void setUp() throws Exception {
        _group = GroupTestUtil.addGroup();
        _user = TestPropsValues.getUser();

        ServiceTestUtil.setUser(TestPropsValues.getUser());
    }

    @Test
    public void testAddFoo() throws Exception {
        int initialCount = _fooLocalService.getFoosCount();

        addFoo(_user.getUserId());

        int actualCount = _fooLocalService.getFoosCount();

        Assert.assertEquals(initialCount + 1, actualCount);
    }


    protected Foo addFoo(long userId)
            throws Exception {
        ServiceContext serviceContext =
                ServiceContextTestUtil.getServiceContext(
                        _group.getGroupId(), userId);

        return _fooLocalService.addFoo(
                userId, RandomTestUtil.randomString(),RandomTestUtil.randomBoolean(),
                RandomTestUtil.randomInt(), RandomTestUtil.nextDate(),RandomTestUtil.randomString(),
                serviceContext);
    }

    @DeleteAfterTestRun
    private Group _group;

    @Inject
    private FooLocalService _fooLocalService;

    private User _user;

}