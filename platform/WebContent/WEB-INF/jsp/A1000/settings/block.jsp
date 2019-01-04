<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
		<div id="waitInfo_zone" class="waitInfo_zone">
			<div class="waitInfo_ztwo">
				<div class="waitInfo_zthd">
					正在处理，请稍候...<span id="time_counter"></span>
				</div>
				<div class="waitInfo_zthd_r">
					<input type="button" value="取 消" onclick="cancelTimerCounter()" class="btn4" onfocus="this.blur()" />
				</div>
			</div>
		</div>