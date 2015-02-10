package br.com.manager.test;

import br.com.manager.infra.dao.IProductRepository;
import br.com.manager.infra.dao.impl.ProductRepositoryJPA;
import br.com.manager.infra.dao.utils.DatabaseTemplateJPA;
import br.com.manager.infra.exception.ProductManagerException;
import br.com.manager.model.entity.BaseEntity;
import br.com.manager.model.filter.ProductFilter;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

import java.io.File;

public class ProductManagerTestUtils {

    public static WebArchive createWebArchiveBase(boolean withDatabase) {
        WebArchive archive = ShrinkWrap.create(WebArchive.class);
        if (withDatabase) {
            archive.addAsResource("db-beans.xml", "META-INF/beans.xml");
            archive.addAsResource("test-persistence.xml", "META-INF/persistence.xml");
            archive.addAsWebInfResource("jbossas-ds.xml");
            addClass(archive, DataBaseTemplateTestJPA.class);
        } else {
            archive.addAsResource("beans.xml", "META-INF/beans.xml");
            addClass(archive, DataBaseTemplateTestJPANoDatabase.class);
        }
        addDefaultDependencies(archive);
        addLibraries(archive, "commons-collections:commons-collections", "commons-lang:commons-lang",
                "commons-beanutils:commons-beanutils");
        return archive;
    }

    public static void addLibraries(WebArchive archive, String... libsStr) {
        File[] libs = Maven.resolver().loadPomFromFile("pom.xml").resolve(libsStr).withTransitivity().asFile();

        for (File file : libs) {
            archive.addAsLibrary(file);
        }
    }

    private static void addDefaultDependencies(Archive<?> archive) {
        addPackage(archive, BaseEntity.class.getPackage());
        addPackage(archive, DatabaseTemplateJPA.class.getPackage());
        addPackage(archive, ProductRepositoryJPA.class.getPackage());
        addPackage(archive, ProductManagerException.class.getPackage());
        addPackage(archive, IProductRepository.class.getPackage());
        addPackage(archive, ProductFilter.class.getPackage());
        addClass(archive, AbstractUnitTest.class);
    }

    public static void addClass(Archive<?> archive, Class<?> classe) {
        if (archive instanceof JavaArchive) {
            ((JavaArchive) archive).addClass(classe);
        } else if (archive instanceof WebArchive) {
            ((WebArchive) archive).addClass(classe);
        }
    }

    public static void addPackage(Archive<?> archive, Package pack) {
        if (archive instanceof JavaArchive) {
            ((JavaArchive) archive).addPackage(pack);
        } else if (archive instanceof WebArchive) {
            ((WebArchive) archive).addPackage(pack);
        }
    }

}
