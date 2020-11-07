package edu.eci.cvds.test;

import edu.eci.cvds.model.services.AuthServices;
import edu.eci.cvds.model.services.LabInfoServicesFactory;
import edu.eci.cvds.model.services.impl.AuthServicesItemStub;
import edu.eci.cvds.test.util.LabInfoTestUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;

@SuppressWarnings("all")
public class LabInfoTest {

    private SqlSession sqlSession;
    private final SqlSessionFactory sqlSessionFactory;

    private final AuthServices authServices;
    private final AuthServices authServicesItemStub;

    public LabInfoTest() {
        sqlSessionFactory = LabInfoTestUtil.getSqlSessionFactory();

        authServices = LabInfoServicesFactory.getInstance().getLabInfoServicesTesting();
        authServicesItemStub = new AuthServicesItemStub();
    }

    @Before
    public void setUp() throws Exception {
        sqlSession = sqlSessionFactory.openSession();
    }
}
