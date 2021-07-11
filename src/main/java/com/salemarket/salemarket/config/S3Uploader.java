package com.salemarket.salemarket.config;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Uploader {
    private final AmazonS3 amazonS3;

    //private final static String TEMP_FILE_PATH = "src/main/resources/";

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // MultipartFile을 전달받아 File로 전환한 후 S3에 업로드
    public String upload(MultipartFile multipartFile, String dirName) throws IOException {
        System.out.println(multipartFile);
        File uploadFile = convert(multipartFile)
                .orElseThrow(() -> new IllegalArgumentException("MultipartFile -> File 전환 실패"));
        return upload(uploadFile, dirName);
    }

    private String upload(File uploadFile, String dirName){
        String fileName = dirName + "/" + UUID.randomUUID().toString().replaceAll("-", ""); // 버킷안에 dirName 폴더로 UUID를 사용해 랜덤이름으로 파일이름 저장
        String uploadImageUrl = putS3(uploadFile, fileName); // S3 업로드

        removeNewFile(uploadFile); //로컬에 생성된 File 삭제(MultipartFile -> File 전환 하며 로컬에 파일 생성됨)
        return uploadImageUrl; // 업로드된 파일의 S3 URL 주소 반환
    }

    private String putS3(File uploadFile, String fileName){
        amazonS3.putObject(
                new PutObjectRequest(bucket, fileName, uploadFile)
                        .withCannedAcl(CannedAccessControlList.PublicRead) // PublicRead 권한으로 업로드 됨
        );
        return amazonS3.getUrl(bucket, fileName).toString(); // Url 받아옴
    }

    private void removeNewFile(File targetFile){ // S3에 올리면서 생성된 로컬파일 삭제
        if(targetFile.delete()){
            log.info("파일이 삭제되었습니다.");
        }else{
            log.info("파일이 삭제되지 못했습니다.");
        }
    }

    private Optional<File> convert(MultipartFile file) throws IOException{ // MultipartFile -> File로 변경
        File convertFile = new File(file.getOriginalFilename());

        if(convertFile.createNewFile()){

            try(FileOutputStream fos = new FileOutputStream(convertFile)){
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }
}
