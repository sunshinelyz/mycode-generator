package com.lyz.code.infinity.s2shc.verb;

import java.util.ArrayList;
import java.util.List;

import com.lyz.code.infinity.core.Verb;
import com.lyz.code.infinity.core.Writeable;
import com.lyz.code.infinity.domain.Domain;
import com.lyz.code.infinity.domain.Method;
import com.lyz.code.infinity.domain.Statement;
import com.lyz.code.infinity.domain.StatementList;
import com.lyz.code.infinity.domain.Type;
import com.lyz.code.infinity.domain.Var;
import com.lyz.code.infinity.generator.NamedStatementGenerator;
import com.lyz.code.infinity.generator.NamedStatementListGenerator;
import com.lyz.code.infinity.s2sh.core.NamedS2SHStatementGenerator;
import com.lyz.code.infinity.s2sh.core.NamedS2SHStatementListGenerator;
import com.lyz.code.infinity.utils.InterVarUtil;
import com.lyz.code.infinity.utils.StringUtil;
import com.lyz.code.infinity.utils.WriteableUtil;

public class ListActive extends Verb{

	@Override
	public Method generateDaoImplMethod(){
		try {
			Method method = new Method();
			method.setStandardName("listActive"+StringUtil.capFirst(this.domain.getPlural()));
			method.setReturnType(new Type("List",this.domain, this.domain.getPackageToken()));
			method.addAdditionalImport("java.util.List");
			method.addAdditionalImport("org.hibernate.Criteria");
			method.addAdditionalImport("org.hibernate.criterion.Restrictions");
			method.addAdditionalImport("org.springframework.orm.hibernate4.support.HibernateDaoSupport");
			method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
			method.setThrowException(true);
			method.addMetaData("Override");
			
			List<Writeable> list = new ArrayList<Writeable>();
			list.add(new Statement(1000L,2, "Session s = this.getHibernateTemplate().getSessionFactory().getCurrentSession();"));
			list.add(new Statement(2000L,2,"Criteria criteria = s.createCriteria("+this.domain.getStandardName()+".class);"));
			list.add(new Statement(3000L,2,"criteria.add(Restrictions.eq(\""+this.domain.getActive().getLowerFirstFieldName()+"\","+this.domain.getDomainActiveStr()+"));"));
			list.add(new Statement(4000L,2,"List<"+this.domain.getStandardName()+"> "+StringUtil.lowerFirst(this.domain.getPlural())+" = (List<"+this.domain.getStandardName()+">)criteria.list();"));
			list.add(new Statement(5000L,2,"return "+StringUtil.lowerFirst(this.domain.getPlural())+";"));
			
			method.setMethodStatementList(WriteableUtil.merge(list));
			return method;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public String generateDaoImplMethodString(){
		return generateDaoImplMethod().generateMethodString();
	}

	@Override
	public Method generateDaoMethodDefinition() {
		Method method = new Method();
		method.setStandardName("listActive"+StringUtil.capFirst(this.domain.getPlural()));
		method.setReturnType(new Type("List",this.domain, this.domain.getPackageToken()));
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		this.additionalImports.add(this.domain.getPackageToken()+"."+this.domain.getStandardName());
		method.setThrowException(true);
		return method;
	}

	@Override
	public String generateDaoMethodDefinitionString() {
		return generateDaoMethodDefinition().generateMethodDefinition();
	}

	@Override
	public String generateDaoImplMethodStringWithSerial() {
		Method m = this.generateDaoImplMethod();
		m.setContent(m.generateMethodContentStringWithSerial());
		m.setMethodStatementList(null);
		return m.generateMethodString();
	}

	@Override
	public Method generateServiceMethodDefinition() {
		Method method = new Method();
		method.setStandardName("listActive"+StringUtil.capFirst(this.domain.getPlural()));
		method.setReturnType(new Type("List",this.domain, this.domain.getPackageToken()));
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.setThrowException(true);
		
		return method;
	}

	@Override
	public String generateServiceMethodDefinitionString() {
		return generateServiceMethodDefinition().generateMethodDefinition();
	}

	@Override
	public Method generateControllerMethod() {
		Method method = new Method();
		method.setStandardName("listActive"+StringUtil.capFirst(this.domain.getPlural()));
		method.setReturnType(new Type("String"));
		method.setThrowException(true);
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".service."+this.domain.getStandardName()+"Service");
		
		List<Writeable> wlist = new ArrayList<Writeable>();
		Var service = new Var("service", new Type(this.domain.getStandardName()+"Service",this.domain.getPackageToken()));
		Var vlist = new Var(this.domain.getLowerFirstDomainName()+"List", new Type("List",this.domain,this.domain.getPackageToken()));
		Var request = new Var("request",new Type("Map","java.util"));
		Method serviceMethod = this.generateServiceMethodDefinition();
		wlist.add(new Statement(1000L,2,vlist.getVarType() + " " + vlist.getVarName() +" = " + service.getVarName()+"."+serviceMethod.getStandardName()+"();"));
		wlist.add(new Statement(2000L,2,this.domain.getLowerFirstDomainName()+StringUtil.capFirst(InterVarUtil.Servlet.request.getVarName())+".put(\""+vlist.getVarName()+"\","+vlist.getVarName()+");"));
		wlist.add(new Statement(3000L,2,"return \""+serviceMethod.getStandardName()+"\";"));
		method.setMethodStatementList(WriteableUtil.merge(wlist));
		
		return method;
	}

	@Override
	public String generateControllerMethodString() {
		return generateControllerMethod().generateMethodString();
	}

	@Override
	public Method generateServiceImplMethod() {
		Method method = new Method();
		method.setStandardName("listActive"+StringUtil.capFirst(this.domain.getPlural()));
		method.setReturnType(new Type("List",this.domain, this.domain.getPackageToken()));
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".dao."+this.domain.getStandardName()+"Dao");
		method.addAdditionalImport(this.domain.getPackageToken()+".daoimpl."+this.domain.getStandardName()+"DaoImpl");
		method.addAdditionalImport(this.domain.getPackageToken()+".service."+this.domain.getStandardName()+"Service");
		method.setThrowException(true);
		method.addMetaData("Override");
		
		Method daomethod = this.generateDaoMethodDefinition();
		
		List<Writeable> list = new ArrayList<Writeable>();
		list.add(NamedS2SHStatementListGenerator.generateServiceImplReturnList(1000L, 2, this.domain, InterVarUtil.DB.dao, daomethod));
		method.setMethodStatementList(WriteableUtil.merge(list));

		return method;
	}

	@Override
	public String generateServiceImplMethodString() {
		return generateServiceImplMethod().generateMethodString();
	}

	@Override
	public String generateServiceImplMethodStringWithSerial() {
		Method m = this.generateServiceImplMethod();
		m.setContent(m.generateMethodContentStringWithSerial());
		m.setMethodStatementList(null);
		return m.generateMethodString();
	}
	
	public ListActive(){
		super();
		if (this.domain != null) this.setVerbName("ListActive"+StringUtil.capFirst(this.domain.getPlural()));
		else this.setVerbName("ListActive");
	}
	
	public ListActive(Domain domain){
		super();
		this.domain = domain;
		this.setVerbName("ListActive"+StringUtil.capFirst(this.domain.getPlural()));
	}

	@Override
	public String generateControllerMethodStringWithSerial() {
		Method m = this.generateControllerMethod();
		m.setContent(m.generateMethodContentStringWithSerial());
		m.setMethodStatementList(null);
		return m.generateMethodString();
	}

	@Override
	public Method generateFacadeMethod() {
		Method method = new Method();
		method.setStandardName("listActive"+StringUtil.capFirst(this.domain.getPlural()));
		method.setReturnType(new Type("String"));
		method.setThrowException(true);
		method.addAdditionalImport("java.util.List");
		method.addAdditionalImport(this.domain.getPackageToken()+".domain."+this.domain.getStandardName());
		method.addAdditionalImport(this.domain.getPackageToken()+".service."+this.domain.getStandardName()+"Service");
		
		List<Writeable> wlist = new ArrayList<Writeable>();
		Var service = new Var("service", new Type(this.domain.getStandardName()+"Service",this.domain.getPackageToken()));
		Var vlist = new Var(this.domain.getLowerFirstDomainName()+"List", new Type("List",this.domain,this.domain.getPackageToken()));
		Var request = new Var("request",new Type("Map","java.util"));
		Method serviceMethod = this.generateServiceMethodDefinition();
		Var resultMap = new Var("result", new Type("TreeMap<String,Object>","java.util"));
		wlist.add(NamedS2SHStatementGenerator.getJsonResultMap(1000L, 2, resultMap));
		wlist.add(new Statement(2000L,2,vlist.getVarType() + " " + vlist.getVarName() +" = " + service.getVarName()+"."+serviceMethod.getStandardName()+"();"));
		wlist.add(NamedS2SHStatementListGenerator.getPutJsonResultMapWithSuccessAndDomainList(3000L, 2, resultMap,vlist));
		wlist.add(NamedS2SHStatementGenerator.getEncodeMapToJsonResultMap(4000L, 2, resultMap));
		wlist.add(NamedS2SHStatementGenerator.getJumpToActionSuccess(5000L, 2));
		method.setMethodStatementList(WriteableUtil.merge(wlist));
		
		return method;
	}

	@Override
	public String generateFacadeMethodString() {
		Method m = this.generateFacadeMethod();
		return m.generateMethodString();
	}

	@Override
	public String generateFacadeMethodStringWithSerial() {
		Method m = this.generateFacadeMethod();
		m.setContent(m.generateMethodContentStringWithSerial());
		m.setMethodStatementList(null);
		return m.generateMethodString();
	}
}
