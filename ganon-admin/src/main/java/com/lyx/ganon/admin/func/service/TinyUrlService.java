package com.lyx.ganon.admin.func.service;

import com.lyx.ganon.admin.func.mapper.FuncTinyUrlMapper;
import com.lyx.ganon.admin.func.model.FuncTinyUrl;
import com.lyx.ganon.admin.func.model.FuncTinyUrlExample;
import com.lyx.ganon.common.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TinyUrlService {

    @Autowired
    private FuncTinyUrlMapper mapper;

    /**
     * 添加路径，如果路径存在则直接返回
     * @param sourceUrl
     * @return
     */
    public String add(String sourceUrl) {
        String md5 = DigestUtils.getMD5(sourceUrl);
        List<FuncTinyUrl> byMd5 = getByMd5(md5);
        Optional<FuncTinyUrl> first = byMd5.stream()
                .filter(tinyUrl -> sourceUrl.equals(tinyUrl.getSrc()))
                .findFirst();
        if (first.isPresent()) {
            return first.get().getPath();
        }

        String path = UUID.randomUUID().toString().substring(0, 10);

        FuncTinyUrl tinyurl = new FuncTinyUrl();
        tinyurl.setPath(path);
        tinyurl.setSrc(sourceUrl);
        tinyurl.setMd5(md5);
        mapper.insertSelective(tinyurl);
        return path;
    }

    private List<FuncTinyUrl> getByMd5(String md5) {
        FuncTinyUrlExample example = new FuncTinyUrlExample();
        example.createCriteria().andMd5EqualTo(md5);
        return mapper.selectByExample(example);
    }

    public String getSrc(String path) {
        FuncTinyUrlExample example = new FuncTinyUrlExample();
        example.createCriteria().andPathEqualTo(path);
        List<FuncTinyUrl> funcTinyUrls = mapper.selectByExample(example);
        FuncTinyUrl tinyUrl = funcTinyUrls.stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("url 错误, " + path));
        return tinyUrl.getSrc();
    }
}
