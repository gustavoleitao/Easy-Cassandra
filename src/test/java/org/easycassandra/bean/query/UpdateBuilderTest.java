package org.easycassandra.bean.query;

import java.util.Random;

import junit.framework.Assert;

import org.easycassandra.bean.dao.PersistenceDao;
import org.easycassandra.persistence.cassandra.UpdateBuilder;
import org.junit.Before;
import org.junit.Test;

/**
 * Query test.
 * @author otaviojava
 */
public class UpdateBuilderTest {

    private PersistenceDao<SimpleBean, Integer> dao = new PersistenceDao<>(
            SimpleBean.class);

    /**
     * run test.
     */
    @Test
    public void updateaddListTest() {
        UpdateBuilder<SimpleBean> update = dao.update();
        update.whereEq(Constant.KEY, Constant.ONE).whereEq(Constant.INDEX, Constant.ONE);
        update.addList("list", "otavioList");
        update.execute();
        SimpleBean bean = getBean();

        Assert.assertTrue(bean.getList() != null && !bean.getList().isEmpty());

    }

    /**
     * run test.
     */
    @Test
    public void updateaddSetTest() {
        UpdateBuilder<SimpleBean> update = dao.update();
        update.whereEq(Constant.KEY, Constant.ONE).whereEq(Constant.INDEX, Constant.ONE);
        update.addSet("set", "otavioSet");
        update.execute();
        SimpleBean bean = getBean();

        Assert.assertTrue(bean.getSet() != null && !bean.getSet().isEmpty());

    }

    /**
     * run test.
     */
    @Test
    public void updateaddMapTest() {
        UpdateBuilder<SimpleBean> update = dao.update();
        update.whereEq(Constant.KEY, Constant.ONE).whereEq(Constant.INDEX, Constant.ONE);
        update.put("map", "name", "otavioMap");
        update.execute();
        SimpleBean bean = getBean();

        Assert.assertTrue(bean.getMap() != null && !bean.getMap().isEmpty());

    }

    private SimpleBean getBean() {
        SimpleID id = new SimpleID();
        id.setIndex(1);
        id.setKey(1);
        SimpleBean bean = dao.retrieve(id);
        return bean;
    }
    /**
     * init.
     */
    @Before
    public void init() {
        Random random = new Random();
        for (int i = 0; i < Constant.SIZE; i++) {
            SimpleBean simple = new SimpleBean();
            simple.getId().setKey(1);
            simple.getId().setIndex(i);
            simple.setName(Constant.NAME);
            simple.setValue(random.nextDouble());
            dao.insert(simple);
        }
    }
}