package com.event.summary.job.tasklet;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.event.summary.properties.ExcelFileProperties;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@AllArgsConstructor
public class FileMoveTasklet implements Tasklet {
  private final ExcelFileProperties excelFileProperties;

  @Override
  public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
    if (excelFileProperties.getShouldMoveFile()) moveProcessedFile();
    return RepeatStatus.FINISHED;
  }

  private void moveProcessedFile() {
    FileSystemResource source = new FileSystemResource(excelFileProperties.getFileLocation());
    FileSystemResource destination =
        new FileSystemResource(excelFileProperties.getOutputFileLocation());
    String destFilePath = destination.getFile().getAbsolutePath();
    String srcFileName = source.getFilename();
    String fileExt = srcFileName.split("\\.")[1];
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
    String formatDateTime = now.format(format);
    String renamedFile =
        srcFileName.split("\\.")[0].concat("-").concat(formatDateTime).concat("." + fileExt);
    File mvFile = new File(destFilePath + "\\" + renamedFile);
    boolean moved = source.getFile().renameTo(mvFile);
  }
}
