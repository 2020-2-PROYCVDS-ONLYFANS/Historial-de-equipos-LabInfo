package edu.eci.cvds.test;

import edu.eci.cvds.model.services.LabInfoServices;
import edu.eci.cvds.model.services.LabInfoServicesFactory;
import edu.eci.cvds.model.services.impl.LabInfoServicesItemStub;
import edu.eci.cvds.test.util.LabInfoTestUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;

@SuppressWarnings("all")
public class LabInfoTest {

    private SqlSession sqlSession;
    private final SqlSessionFactory sqlSessionFactory;

    private final LabInfoServices labInfoServices;
    private final LabInfoServices labInfoServicesItemStub;

    public LabInfoTest() {
        sqlSessionFactory = LabInfoTestUtil.getSqlSessionFactory();

        labInfoServices = LabInfoServicesFactory.getInstance().getLabInfoServicesTesting();
        labInfoServicesItemStub = new LabInfoServicesItemStub();
    }

    @Before
    public void setUp() throws Exception {
        sqlSession = sqlSessionFactory.openSession();
    }
}
