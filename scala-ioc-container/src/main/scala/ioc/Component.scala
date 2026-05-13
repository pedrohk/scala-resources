package ioc

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Retention(RetentionPolicy.RUNTIME)
final class Component extends scala.annotation.StaticAnnotation