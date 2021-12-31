package com.chinasofti.postbar.service;

import com.chinasofti.postbar.dto.AudioDto;

/**
 * Created by LinYaTing on 2019/6/5 23:07
 */
public interface AudioService {

    AudioDto getAudioSetUp(String id);

    boolean updateAudioSetUp(AudioDto audioDto);
}
