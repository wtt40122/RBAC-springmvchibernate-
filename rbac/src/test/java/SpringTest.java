import java.util.Date;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wt.cms.service.RoleService;

/**
 * @description:SpringTest
 * @author wt
 * @date 2017-11-20
 */
@RunWith(SpringJUnit4ClassRunner.class)
/*@ContextConfiguration(locations="classpath:spring/spring-*.xml")*/
@ContextConfiguration(locations = "/spring/spring-*.xml") //设置spring配置文件路径
public class SpringTest {
	@Autowired
	public Date date;
	
	@Autowired
	public RoleService roleService;
	@Test
	public void springIocTest(){
		System.out.println(date);
	}
	@Test
	public void getRoleById(){
	}
}
