package maxima.ru.filemanager.repository;

import maxima.ru.filemanager.BasicTestContainer;
import maxima.ru.filemanager.model.File;
import maxima.ru.filemanager.repository.FileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.Optional;



class FileRepositoryTests extends BasicTestContainer {

@Autowired
private FileRepository fileRepository;

@Test
@Transactional
public void createFileTest(){
	File file = getFile();
	File save = fileRepository.save(file);
	Assertions.assertEquals(file.getTitle(), save.getTitle());
	Assertions.assertEquals(file.getDescribe(), save.getDescribe());
	Assertions.assertEquals(file.getType(), save.getType());
	Assertions.assertEquals(file.getDestiny(), save.getDestiny());
	Assertions.assertNotNull(save.getId());
}

	@Test
	@Transactional
	public void editFileTest() {
		File file = getFile();
		File save = fileRepository.save(file);
		save.setDescribe("New describe");
		fileRepository.save(save);
		Optional<File> newSave = fileRepository.findById(save.getId());
		Assertions.assertTrue(newSave.isPresent());
		File file1 = newSave.get();
		Assertions.assertEquals(file.getTitle(), file1.getTitle());
		Assertions.assertEquals(file.getDescribe(), file1.getDescribe());
		Assertions.assertEquals(file.getType(), file1.getType());
		Assertions.assertEquals(file.getDestiny(), file1.getDestiny());
		Assertions.assertNotNull(file1.getId());
	}

	private File getFile(){
		return File.builder()
				.title("Test title")
				.describe("Test describe")
				.type("Test type")
				.destiny("Test destiny")
				.build();
	}
}
