package com.chinasofti.postbar.service.impl;

import com.chinasofti.postbar.dto.AudioDto;
import com.chinasofti.postbar.mapper.PbAudioMapper;
import com.chinasofti.postbar.service.AudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by LinYaTing on 2019/6/5 23:07
 */
@Service
public class AudioServiceImpl implements AudioService {

    @Autowired
    private PbAudioMapper audioMapper;

    @Override
    public AudioDto getAudioSetUp(String id) {
        return audioMapper.selectAudioByUserUUID(id);
    }

    @Override
    public boolean updateAudioSetUp(AudioDto audioDto) {
        int row = audioMapper.updateAudioByAuSetUUID(audioDto);
        if (row >= 1) {
            return true;
        }
        return false;
    }
}
