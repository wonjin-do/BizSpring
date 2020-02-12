package com.cal.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cal.mapper.CalendarMapper;
import com.cal.vo.CalDTO;
import com.cal.vo.DayInfo;
import com.cal.vo.HolidayVO;
import com.cal.vo.MemberVO;
import com.cal.vo.ScheduleVO;
import com.cal.vo.previousVersion.CalendarVO;

import lombok.extern.java.Log;
@Service
@Log
public class CalendarServiceImpl implements CalendarService{
	@Autowired
	CalendarMapper calendarMapper;

	@Override
	public CalDTO  showCalendar( int year, int month, String id){
		// TODO Auto-generated method stub
		
		//calDTO 만들
		CalDTO calDTO = new CalDTO(year, month);
		List<DayInfo> days1D = calDTO.getDays1D();
		List<List<DayInfo>> days2D = calDTO.getDays2D();
		
		//1D
		for(int i=calDTO.getDayOfweek() - 1; i >= 1; i--) {
			int day = calDTO.getPrevEndDay() - i + 1;
			days1D.add(new DayInfo(calDTO.getPrevYear(), calDTO.getPrevMonth(), day));
		}
		for(int i = 0; i < calDTO.getLastDay(); i++) {
			days1D.add(new DayInfo(year, month, i+1));
		}
		for(int i = 0; i < 7; i++) {
			days1D.add(new DayInfo(calDTO.getNextYear(), calDTO.getNextMonth(), i+1));
		}
		//2D
		for(int i = 0; i < calDTO.getNumOfweeks(); i++) {
			days2D.add(days1D.subList(i * CalDTO.PREIOD_int, (i+1) * CalDTO.PREIOD_int));
		}
		
		return calDTO;
	}
	
	@Override
	public Map<String, List<HolidayVO>> getHoliday(int year, int month){
		//holiday 만듬
		Map<String, List<HolidayVO>> holidays = getHolidaysMap();
		
		//연 , 월을 이용한 필터링
		
		return holidays;
	}
	
	@Override
	public Map<String, List<ScheduleVO>> getSchedule(int year, int month, String id) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int lastDay = cal.getActualMaximum(Calendar.DATE);

		cal.set(year, month-1, 1);
		String firstdate = sdf.format(cal.getTime());
		cal.set(year, month-1, lastDay);
		String lastDate = sdf.format(cal.getTime());
		System.out.println(firstdate + " " + lastDate);
		List<ScheduleVO> scheduleList = calendarMapper.getSchedule(firstdate, lastDate, id);
		System.out.println("스케줄 갯수: " + scheduleList.size());
		Map<String, List<ScheduleVO>> scheduleMap = new HashMap<String, List<ScheduleVO>>();
		
		List<ScheduleVO> value;
		for(ScheduleVO schedule : scheduleList) {
			String key = schedule.getStartdate();
			if(!scheduleMap.containsKey(key)) {
				value = new ArrayList<ScheduleVO>();
				value.add(schedule);
				scheduleMap.put(key, value);
			}else {
				value = scheduleMap.get(key);
				value.add(schedule);
			}
		}
		return scheduleMap;
	}
	
	@Override
	public int setSchedule(ScheduleVO vo) {
		// TODO Auto-generated method stub
		int cnt = calendarMapper.setSchedule(vo);
		return cnt;
	}
	
	@Override
	public void register(MemberVO memberVO) {
		// TODO Auto-generated method stub
		calendarMapper.register(memberVO);
	}

	@Override
	public boolean login(MemberVO member) {
		// TODO Auto-generated method stub
		
		MemberVO res = calendarMapper.getMember(member);
		if(res != null)
			return true;
		return false;
	}

	
	//공휴일 파일읽어오기
	public Map<String, List<HolidayVO>> getHolidaysMap() {
		Map<String, List<HolidayVO>> holidayMap = new HashMap<>();
		List <HolidayVO> value;
		try{
            //파일 객체 생성
            File file = new File("C:\\Users\\Bizspring\\Desktop\\holiday.txt");
            //입력 스트림 생성
            FileReader filereader = new FileReader(file);
            //입력 버퍼 생성
            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            while((line = bufReader.readLine()) != null){
            	//라인단위로 읽는 함수는 \n 을 읽어오진 않음
            	String [] rowParts = line.split("\t"); // "2020-01-26"  "설연휴"   "Y"
    			HolidayVO holidayVO = new HolidayVO(rowParts[1], rowParts[2]);
    			String key = rowParts[0];
    			if(!holidayMap.containsKey(rowParts[0])) {
    				value = new ArrayList<HolidayVO>();
    				value.add(holidayVO);
    				holidayMap.put(key, value);
    			}else {
    				value = holidayMap.get(key);
    				value.add(holidayVO);
    			}
            }
            filereader.close();
            bufReader.close();
        }catch (FileNotFoundException e) {
            // TODO: handle exception
        }catch(IOException e){
            System.out.println(e);
        }
		
		return holidayMap;
	}



	//죽은 코드
	@Override
	public CalendarVO getHome(CalendarVO cldVO, String id)  {
//		// TODO Auto-generated method stub
//		int year ;
//		int month;
//		int endDay;
//		int dayOfWeek;
//		
//		int prevMonth;
//		int prevEndDay;
//		
//		int cell = 1;
//		int days[][] = new int[totalCell][2];
//		int numOfRows;
//		String meaning[] = new String[totalCell];
//		Calendar cal = Calendar.getInstance();
//		SimpleDateFormat sdf;
//		year = (cldVO.getYear() == 0)?  Calendar.getInstance().get(Calendar.YEAR):  cldVO.getYear();
//		month = (cldVO.getMonth() == 0)?  Calendar.getInstance().get(Calendar.MONTH):  cldVO.getMonth();
//		prevMonth = (month == 1) ? 12 : month -1;
//		
//		//이전 달
//		cal.set(year, prevMonth - 1, 1); 				  //동시 세팅 연,월,일 / month는 0이 1월이므로 -1을 해준다
//		prevEndDay = cal.getActualMaximum(Calendar.DATE); // 해당 월 마지막 날짜 28, 30, 31
//		
//		//이번 달
//		cal.set(year, month - 1, 1); 					//동시 세팅 연,월,일 / month는 0이 1월이므로 -1을 해준다
//		endDay = cal.getActualMaximum(Calendar.DATE);   // 해당 월 마지막 날짜 28, 30, 31
//		dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);      // 해당 날짜의 요일 숫자값(1:일요일 … 7:토요일)
//		
//		numOfRows =(int)Math.ceil((dayOfWeek + endDay - 1)/7.0);
//		
//		
//		//지난 달 일수 채우기
//		for(;cell<dayOfWeek;cell++) {
//			days[cell][0] = prevEndDay - dayOfWeek + cell;
//		}
//		//현재 달 일수 채우기
//		sdf = new SimpleDateFormat("yyyy");
//		
//		Map<String, List<HolidayVO>> holidays = getHolidaysMap();         //현재 달 공휴일 
//		for(Map.Entry<String, List<HolidayVO>> holi : holidays.entrySet()) {
////			Date date = holi.getKey();
////			String strYear = sdf.format(date);
////			String strMonth = sdf.format(date);
////			String strDay = sdf.format(date);
////			int intYear= Integer.parseInt(strYear);
////			int intMonth = Integer.parseInt(strMonth);
////			int intDay = Integer.parseInt(strDay);
//		}
//
//		//다음 달 일수 채우기
//		for(int day=1;cell<=days.length-1;cell++,day++) {
//			days[cell][0] = day;
//		}
//		//days end
//		System.out.println("month: "+month);
//		String strMonth = Integer.toString(month);
//		if(month < 10)
//			strMonth = "0"+strMonth;
//		String strYear = Integer.toString(year);
//		String strTot1 = strYear + "-" + strMonth + "-" + "01";
//		String strTot2 = strYear + "-" + strMonth + "-" + "31";
//		System.out.println(strTot1);
//		System.out.println(strTot2);
//		List<ScheduleVO> scheduleList = calendarMapper.getSchedule(strTot1, strTot2, id);
//		System.out.println("test: " + scheduleList.size());
//	
//		for(ScheduleVO x: scheduleList) {
//			System.out.println(x);
//		}
//		
//		cldVO = new CalendarVO(year, month, endDay, dayOfWeek, numOfRows, days, meaning, scheduleList);
		return cldVO;
	}

	

}
