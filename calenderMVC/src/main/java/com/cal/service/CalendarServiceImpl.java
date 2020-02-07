package com.cal.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cal.mapper.CalendarMapper;
import com.cal.vo.CalendarVO;
import com.cal.vo.MemberVO;

import lombok.extern.java.Log;
@Service
@Log
public class CalendarServiceImpl implements CalendarService{
	private static final int totalCell = 42 +1;

	@Autowired
	CalendarMapper calendarMapper;
	
	@Override
	public CalendarVO getHome(CalendarVO cldVO) throws ParseException {
		// TODO Auto-generated method stub
		int year = (cldVO.getYear() == 0 || cldVO.getMonth()==0)?  Calendar.getInstance().get(Calendar.YEAR):  cldVO.getYear();
		int month = (cldVO.getYear() == 0 || cldVO.getMonth()==0)?  Calendar.getInstance().get(Calendar.MONTH):  cldVO.getMonth();
		int endDay;
		int dayOfWeek;
		int prevMonth = (month == 1) ? 12 : month -1;
		int prevEndDay;
		int cell = 1;
		int days[][] = new int[totalCell][2];
		int numOfRows;
		String meaning[] = new String[totalCell];
		
		log.info("year: "+year);
		//List<Date> holidayList = calendarService.getHodliday(year, month);//db로 부터 공휴일 불러오기

		Calendar cal = Calendar.getInstance();			  // cal.set(Calendar.YEAR, year); // 입력받은 년도로 세팅	// cal.set(Calendar.MONTH, month); // 입력받은 월로 세팅
		
		//이전 달
		cal.set(year, prevMonth - 1, 1); 				  //동시 세팅 연,월,일 / month는 0이 1월이므로 -1을 해준다
		prevEndDay = cal.getActualMaximum(Calendar.DATE); // 해당 월 마지막 날짜 28, 30, 31
		
		//이번 달
		cal.set(year, month - 1, 1); 					//동시 세팅 연,월,일 / month는 0이 1월이므로 -1을 해준다
		endDay = cal.getActualMaximum(Calendar.DATE);   // 해당 월 마지막 날짜 28, 30, 31
		dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);      // 해당 날짜의 요일 숫자값(1:일요일 … 7:토요일)
		
		numOfRows =(int)Math.ceil((dayOfWeek + endDay - 1)/7.0);
		
		
		//지난 달 일수 채우기
		for(;cell<dayOfWeek;cell++) {
			days[cell][0] = prevEndDay - dayOfWeek + cell;
		}
		//현재 달 일수 채우기
		SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdfMonth = new SimpleDateFormat("MM");
		SimpleDateFormat sdfDay = new SimpleDateFormat("dd");
		Map<Date, String> holidays = getHolidays();         //현재 달 공휴일 
		for(int day=1; day <= endDay; cell++, day++) {
			days[cell][0] =  day;
			
			for(Map.Entry<Date, String> holi : holidays.entrySet()) {
				Date part = holi.getKey();
				String strYear = sdfYear.format(part);
				String strMonth = sdfMonth.format(part);
				String strDay = sdfDay.format(part);
				int intYear= Integer.parseInt(strYear);
				int intMonth = Integer.parseInt(strMonth);
				int intDay = Integer.parseInt(strDay);
				if(intYear==year && intMonth==month && intDay==day) {
					days[cell][1] = 1;// 휴일이다.
					meaning[cell] = holi.getValue();
				}
			}
		}

		//다음 달 일수 채우기
		for(int day=1;cell<=days.length-1;cell++,day++) {
			days[cell][0] = day;
		}
		cldVO = new CalendarVO(year, month, endDay, dayOfWeek, numOfRows, days, meaning);
		return cldVO;
	}
	
	@Override
	public void register(MemberVO memberVO) {
		// TODO Auto-generated method stub
		calendarMapper.register(memberVO);
	}

	@Override
	public List<Date> getHodliday(int year, int month) {
		// TODO Auto-generated method stub
		 String len2month = String.format("%02d", month);
		return calendarMapper.getHoliday(year, len2month);
	}

	@Override
	public boolean login(MemberVO member) {
		// TODO Auto-generated method stub
		
		MemberVO res = calendarMapper.getMember(member);
		if(res != null)
			return true;
		return false;
	}

	public Map<Date,String> getHolidays() throws ParseException{
        String res = "";
		try{
            //파일 객체 생성
            File file = new File("C:\\holiday.txt");
            //입력 스트림 생성
            FileReader filereader = new FileReader(file);
            int singleCh = 0;
            while((singleCh = filereader.read()) != -1){
                res += (char)singleCh;
            }
            filereader.close();
            
        }catch (FileNotFoundException e) {
            // TODO: handle exception
        }catch(IOException e){
            System.out.println(e);
        }
		String[] rows =  res.split("\\t");
		Map<Date, String> holidays = new HashMap<>();
		for(String row : rows) {
			String[] parts = row.split(" ");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			holidays.put(sdf.parse(parts[0]), parts[1]);
		}
		return holidays;
	}



}
