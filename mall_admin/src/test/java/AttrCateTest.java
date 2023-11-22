import com.jasper.MallAdminApplication;
import com.jasper.mapper.GoodsAttributeCategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = MallAdminApplication.class)
public class AttrCateTest {

    @Autowired
    GoodsAttributeCategoryMapper attributeCategoryMapper;

    @Test
    public void test(){
        System.out.println(attributeCategoryMapper.getListWithAttr());
    }
}
